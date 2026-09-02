package com.example.util

import android.content.Context
import android.net.Uri
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.util.zip.Inflater
import java.util.zip.ZipInputStream

object DocumentTextExtractor {
    private const val TAG = "DocumentTextExtractor"

    suspend fun extractTextFromUri(context: Context, uri: Uri): Result<ExtractedDocument> = withContext(Dispatchers.IO) {
        try {
            val contentResolver = context.contentResolver
            val mimeType = contentResolver.getType(uri) ?: ""
            val fileName = uri.lastPathSegment ?: "document"

            val inputStream = contentResolver.openInputStream(uri)
                ?: return@withContext Result.failure(Exception("Cannot open file stream"))

            val bytes = inputStream.use { it.readBytes() }

            val text = when {
                mimeType.contains("pdf", ignoreCase = true) || fileName.endsWith(".pdf", ignoreCase = true) -> {
                    extractTextFromPdfBytes(bytes)
                }
                fileName.endsWith(".epub", ignoreCase = true) -> {
                    extractTextFromEpubBytes(bytes)
                }
                else -> {
                    // Plain text / markdown / general text
                    String(bytes, Charsets.UTF_8)
                }
            }

            val cleanedText = cleanAndNormalizeText(text)
            val paragraphs = splitIntoParagraphs(cleanedText)

            Result.success(
                ExtractedDocument(
                    fileName = fileName.substringAfterLast('/'),
                    fullText = cleanedText,
                    paragraphs = paragraphs,
                    estimatedWordCount = cleanedText.split("\\s+".toRegex()).count { it.isNotBlank() }
                )
            )
        } catch (e: Exception) {
            Log.e(TAG, "Error extracting document text", e)
            Result.failure(e)
        }
    }

    fun extractTextFromPdfBytes(bytes: ByteArray): String {
        val stringContent = StringBuilder()
        try {
            // First attempt: Stream and object parser for standard PDF text
            val textBlocks = extractStreamsFromPdf(bytes)
            for (stream in textBlocks) {
                val parsed = parsePdfStreamText(stream)
                if (parsed.isNotBlank()) {
                    stringContent.append(parsed).append("\n\n")
                }
            }

            // If parsed stream text is too short or empty, fallback to raw string scanning
            if (stringContent.length < 50) {
                val rawText = scanPdfLiteralStrings(bytes)
                if (rawText.length > stringContent.length) {
                    return rawText
                }
            }
        } catch (e: Exception) {
            Log.w(TAG, "PDF parsing warning, using fallback scanner", e)
            return scanPdfLiteralStrings(bytes)
        }

        return if (stringContent.isNotBlank()) stringContent.toString() else scanPdfLiteralStrings(bytes)
    }

    private fun extractStreamsFromPdf(bytes: ByteArray): List<ByteArray> {
        val streams = mutableListOf<ByteArray>()
        var index = 0
        val len = bytes.size

        while (index < len - 6) {
            // Look for "stream" token
            if (bytes[index] == 's'.code.toByte() &&
                bytes[index + 1] == 't'.code.toByte() &&
                bytes[index + 2] == 'r'.code.toByte() &&
                bytes[index + 3] == 'e'.code.toByte() &&
                bytes[index + 4] == 'a'.code.toByte() &&
                bytes[index + 5] == 'm'.code.toByte()
            ) {
                var start = index + 6
                // skip whitespace / newline after "stream"
                while (start < len && (bytes[start] == '\r'.code.toByte() || bytes[start] == '\n'.code.toByte() || bytes[start] == ' '.code.toByte())) {
                    start++
                }

                // Search for "endstream"
                var end = start
                var foundEnd = false
                while (end < len - 9) {
                    if (bytes[end] == 'e'.code.toByte() &&
                        bytes[end + 1] == 'n'.code.toByte() &&
                        bytes[end + 2] == 'd'.code.toByte() &&
                        bytes[end + 3] == 's'.code.toByte() &&
                        bytes[end + 4] == 't'.code.toByte() &&
                        bytes[end + 5] == 'r'.code.toByte() &&
                        bytes[end + 6] == 'e'.code.toByte() &&
                        bytes[end + 7] == 'a'.code.toByte() &&
                        bytes[end + 8] == 'm'.code.toByte()
                    ) {
                        foundEnd = true
                        break
                    }
                    end++
                }

                if (foundEnd && end > start) {
                    val streamData = bytes.copyOfRange(start, end)
                    val decompressed = tryDecompressFlate(streamData)
                    streams.add(decompressed ?: streamData)
                    index = end + 9
                } else {
                    index += 6
                }
            } else {
                index++
            }
        }
        return streams
    }

    private fun tryDecompressFlate(data: ByteArray): ByteArray? {
        return try {
            val inflater = Inflater()
            inflater.setInput(data)
            val outputStream = ByteArrayOutputStream(data.size * 2)
            val buffer = ByteArray(4096)
            while (!inflater.finished()) {
                val count = inflater.inflate(buffer)
                if (count <= 0 && inflater.needsInput()) break
                if (count <= 0) break
                outputStream.write(buffer, 0, count)
            }
            inflater.end()
            val result = outputStream.toByteArray()
            if (result.isNotEmpty()) result else null
        } catch (e: Exception) {
            null
        }
    }

    private fun parsePdfStreamText(streamBytes: ByteArray): String {
        val streamStr = String(streamBytes, Charsets.ISO_8859_1)
        val sb = StringBuilder()

        // Match Tj (single string) and TJ (array of strings) operators in PDF
        val tjRegex = Regex("""\((.*?)\)\s*Tj""", RegexOption.DOT_MATCHES_ALL)
        val tjMatches = tjRegex.findAll(streamStr)
        for (m in tjMatches) {
            val text = unescapePdfString(m.groupValues[1])
            if (text.isNotBlank()) {
                sb.append(text).append(" ")
            }
        }

        val arrayTjRegex = Regex("""\[(.*?)\]\s*TJ""", RegexOption.DOT_MATCHES_ALL)
        val arrayMatches = arrayTjRegex.findAll(streamStr)
        for (m in arrayMatches) {
            val insideArray = m.groupValues[1]
            val subStrings = Regex("""\((.*?)\)""").findAll(insideArray)
            for (sub in subStrings) {
                val text = unescapePdfString(sub.groupValues[1])
                sb.append(text)
            }
            sb.append(" ")
        }

        return sb.toString().trim()
    }

    private fun scanPdfLiteralStrings(bytes: ByteArray): String {
        val sb = StringBuilder()
        var i = 0
        val len = bytes.size

        while (i < len) {
            if (bytes[i] == '('.code.toByte()) {
                val start = i + 1
                var depth = 1
                var j = start
                var escaped = false

                while (j < len && depth > 0) {
                    if (!escaped && bytes[j] == '\\'.code.toByte()) {
                        escaped = true
                    } else {
                        if (!escaped) {
                            if (bytes[j] == '('.code.toByte()) depth++
                            else if (bytes[j] == ')'.code.toByte()) depth--
                        }
                        escaped = false
                    }
                    j++
                }

                if (depth == 0 && j > start) {
                    val rawStr = String(bytes.copyOfRange(start, j - 1), Charsets.ISO_8859_1)
                    val unescaped = unescapePdfString(rawStr)
                    // Filter out binary-like strings
                    if (isPrintableText(unescaped) && unescaped.length > 2) {
                        sb.append(unescaped).append(" ")
                    }
                    i = j
                } else {
                    i++
                }
            } else {
                i++
            }
        }

        return sb.toString().trim()
    }

    private fun unescapePdfString(input: String): String {
        return input
            .replace("\\n", "\n")
            .replace("\\r", "\r")
            .replace("\\t", "\t")
            .replace("\\(", "(")
            .replace("\\)", ")")
            .replace("\\\\", "\\")
    }

    private fun isPrintableText(str: String): Boolean {
        if (str.isBlank()) return false
        val lettersOrDigits = str.count { it.isLetterOrDigit() || it.isWhitespace() }
        return lettersOrDigits.toFloat() / str.length.toFloat() > 0.7f
    }

    private fun extractTextFromEpubBytes(bytes: ByteArray): String {
        val sb = StringBuilder()
        try {
            val zip = ZipInputStream(bytes.inputStream())
            var entry = zip.nextEntry
            while (entry != null) {
                if (entry.name.endsWith(".html", ignoreCase = true) ||
                    entry.name.endsWith(".xhtml", ignoreCase = true) ||
                    entry.name.endsWith(".htm", ignoreCase = true)
                ) {
                    val html = String(zip.readBytes(), Charsets.UTF_8)
                    val stripped = stripHtmlTags(html)
                    if (stripped.isNotBlank()) {
                        sb.append(stripped).append("\n\n")
                    }
                }
                zip.closeEntry()
                entry = zip.nextEntry
            }
        } catch (e: Exception) {
            Log.e(TAG, "EPUB extraction error", e)
        }
        return sb.toString()
    }

    private fun stripHtmlTags(html: String): String {
        return html
            .replace(Regex("<script.*?</script>", RegexOption.DOT_MATCHES_ALL), "")
            .replace(Regex("<style.*?</style>", RegexOption.DOT_MATCHES_ALL), "")
            .replace(Regex("<br\\s*/?>", RegexOption.IGNORE_CASE), "\n")
            .replace(Regex("</p>", RegexOption.IGNORE_CASE), "\n\n")
            .replace(Regex("<[^>]+>"), " ")
            .replace("&nbsp;", " ")
            .replace("&amp;", "&")
            .replace("&quot;", "\"")
            .replace("&lt;", "<")
            .replace("&gt;", ">")
            .trim()
    }

    private fun cleanAndNormalizeText(raw: String): String {
        return raw
            .replace("\r\n", "\n")
            .replace("\r", "\n")
            .replace(Regex("[ \\t]+"), " ")
            .replace(Regex("\n{3,}"), "\n\n")
            .trim()
    }

    fun splitIntoParagraphs(text: String): List<String> {
        if (text.isBlank()) return emptyList()

        val rawBlocks = text.split("\n\n")
            .map { it.trim() }
            .filter { it.isNotBlank() }

        if (rawBlocks.size > 1) {
            return rawBlocks
        }

        // If it's a single block with single line breaks, group into sentences or chunks of ~3-4 sentences
        val sentences = text.split(Regex("(?<=[.!?])\\s+"))
            .map { it.trim() }
            .filter { it.isNotBlank() }

        val paragraphs = mutableListOf<String>()
        var currentChunk = StringBuilder()
        var sentenceCount = 0

        for (s in sentences) {
            currentChunk.append(s).append(" ")
            sentenceCount++
            if (sentenceCount >= 3 || currentChunk.length > 250) {
                paragraphs.add(currentChunk.toString().trim())
                currentChunk = StringBuilder()
                sentenceCount = 0
            }
        }

        if (currentChunk.isNotBlank()) {
            paragraphs.add(currentChunk.toString().trim())
        }

        return if (paragraphs.isNotEmpty()) paragraphs else listOf(text)
    }
}

data class ExtractedDocument(
    val fileName: String,
    val fullText: String,
    val paragraphs: List<String>,
    val estimatedWordCount: Int
)
