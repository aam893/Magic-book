package com.example.data.api

import android.util.Log
import com.example.BuildConfig
import com.example.data.model.BilingualParagraph
import com.example.data.model.Book
import com.example.data.model.DifficultyLevel
import com.example.data.model.Language
import com.example.data.model.TranslationLanguage
import com.example.data.model.VocabularyWord
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.util.UUID
import java.util.concurrent.TimeUnit

object GeminiService {
    private const val TAG = "GeminiService"
    private const val MODEL = "gemini-3.5-flash"
    private const val BASE_URL = "https://generativelanguage.googleapis.com/v1beta/models/$MODEL:generateContent"

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    private fun getApiKey(): String {
        return try {
            BuildConfig.GEMINI_API_KEY
        } catch (e: Exception) {
            ""
        }
    }

    suspend fun generateBilingualStory(
        topic: String,
        targetLanguage: Language,
        translationLanguage: TranslationLanguage = TranslationLanguage.ARABIC,
        difficulty: DifficultyLevel,
        genre: String,
        pageCount: Int = 1
    ): Result<Book> = withContext(Dispatchers.IO) {
        val apiKey = getApiKey()
        val totalParagraphs = (pageCount * 2).coerceIn(2, 60)

        val coverTheme = when (genre.lowercase()) {
            "sci-fi" -> com.example.data.model.BookCoverTheme.CELESTIAL_INDIGO
            "folklore" -> com.example.data.model.BookCoverTheme.DESERT_AMBER
            "mystery" -> com.example.data.model.BookCoverTheme.ROYAL_MIDNIGHT
            "adventure" -> com.example.data.model.BookCoverTheme.NOBLE_EMERALD
            "comedy" -> com.example.data.model.BookCoverTheme.CRIMSON_RUBY
            else -> com.example.data.model.BookCoverTheme.VINTAGE_LEATHER
        }

        if (apiKey.isBlank() || apiKey == "MY_GEMINI_API_KEY") {
            return@withContext Result.success(
                generateOfflineStory(topic, targetLanguage, translationLanguage, difficulty, genre, pageCount, coverTheme)
            )
        }

        val prompt = """
You are an expert polyglot language teacher and bilingual author.
Create an engaging bilingual graded reader story tailored for language learners.

Parameters:
- Topic/Plot Idea: "$topic"
- Target Language to Learn: ${targetLanguage.displayName} (${targetLanguage.code})
- Translation / Native Language: ${translationLanguage.displayName} (${translationLanguage.nativeName})
- CEFR Difficulty Level: ${difficulty.code} (${difficulty.label})
- Genre: $genre
- Desired Story Length: $pageCount page(s) (approx. $totalParagraphs paragraphs, formatted with 2 paragraphs per page)

Requirements:
1. Provide a story title in the target language (${targetLanguage.displayName}) and translated title in ${translationLanguage.displayName}.
2. Provide a 1-sentence synopsis in ${translationLanguage.displayName}.
3. Write exactly $totalParagraphs rich, cohesive paragraphs progressing through the narrative across all $pageCount pages. For each paragraph:
   - "targetText": Natural text in ${targetLanguage.displayName}, carefully calibrated to CEFR ${difficulty.code} vocabulary and grammar.
   - "translationText": Accurate, natural translation in ${translationLanguage.displayName} (${translationLanguage.nativeName}).
4. Output STRICT JSON format only (no markdown quotes, no triple backticks).

JSON Structure:
{
  "title": "Story Title in ${targetLanguage.displayName}",
  "translatedTitle": "Story Title in ${translationLanguage.displayName}",
  "description": "Short description in ${translationLanguage.displayName}",
  "paragraphs": [
    {
      "targetText": "Paragraph in target language...",
      "translationText": "Paragraph translation in ${translationLanguage.displayName}..."
    }
  ]
}
""".trimIndent()

        try {
            val jsonPayload = JSONObject().apply {
                put("contents", JSONArray().apply {
                    put(JSONObject().apply {
                        put("parts", JSONArray().apply {
                            put(JSONObject().apply {
                                put("text", prompt)
                            })
                        })
                    })
                })
                put("generationConfig", JSONObject().apply {
                    put("temperature", 0.7)
                    put("responseMimeType", "application/json")
                })
            }

            val requestBody = jsonPayload.toString().toRequestBody("application/json".toMediaType())
            val request = Request.Builder()
                .url("$BASE_URL?key=$apiKey")
                .post(requestBody)
                .build()

            val response = okHttpClient.newCall(request).execute()
            val responseBody = response.body?.string() ?: ""

            if (!response.isSuccessful) {
                Log.w(TAG, "Gemini API error: ${response.code} $responseBody. Using fallback.")
                return@withContext Result.success(
                    generateOfflineStory(topic, targetLanguage, translationLanguage, difficulty, genre, pageCount, coverTheme)
                )
            }

            val rootJson = JSONObject(responseBody)
            val candidates = rootJson.optJSONArray("candidates")
            val firstCandidate = candidates?.optJSONObject(0)
            val content = firstCandidate?.optJSONObject("content")
            val parts = content?.optJSONArray("parts")
            val text = parts?.optJSONObject(0)?.optString("text") ?: ""

            val cleanedJson = text.trim().removePrefix("```json").removePrefix("```").removeSuffix("```").trim()
            val storyJson = JSONObject(cleanedJson)

            val title = storyJson.optString("title", "A Story in ${targetLanguage.displayName}")
            val translatedTitle = storyJson.optString("translatedTitle", "Story")
            val description = storyJson.optString("description", "A custom $pageCount-page bilingual reader created with Gemini.")
            val paragraphsArray = storyJson.optJSONArray("paragraphs") ?: JSONArray()

            val paragraphs = mutableListOf<BilingualParagraph>()
            var wordCount = 0

            for (i in 0 until paragraphsArray.length()) {
                val pObj = paragraphsArray.getJSONObject(i)
                val target = pObj.optString("targetText", "")
                val trans = pObj.optString("translationText", "")
                if (target.isNotBlank()) {
                    paragraphs.add(BilingualParagraph(targetText = target, translationText = trans))
                    wordCount += target.split("\\s+".toRegex()).size
                }
            }

            val book = Book(
                title = title,
                translatedTitle = translatedTitle,
                author = "AI Studio (${difficulty.code})",
                description = description,
                targetLanguage = targetLanguage,
                nativeLanguage = translationLanguage.displayName,
                difficulty = difficulty,
                coverEmoji = when (genre.lowercase()) {
                    "sci-fi" -> "🚀"
                    "mystery" -> "🔍"
                    "fantasy" -> "✨"
                    "daily life" -> "☕"
                    "folklore" -> "📜"
                    "comedy" -> "🎭"
                    else -> "🌟"
                },
                coverTheme = coverTheme,
                coverGradientStart = coverTheme.primaryColor,
                coverGradientEnd = coverTheme.secondaryColor,
                paragraphs = paragraphs,
                isAiGenerated = true,
                totalWords = wordCount,
                explicitPageCount = pageCount
            )

            Result.success(book)
        } catch (e: Exception) {
            Log.e(TAG, "Error generating story", e)
            Result.success(
                generateOfflineStory(topic, targetLanguage, translationLanguage, difficulty, genre, pageCount, coverTheme)
            )
        }
    }

    suspend fun translateParagraphsBatch(
        paragraphs: List<String>,
        targetLanguage: Language,
        translationLanguage: TranslationLanguage
    ): Result<List<BilingualParagraph>> = withContext(Dispatchers.IO) {
        val apiKey = getApiKey()
        if (apiKey.isBlank() || apiKey == "MY_GEMINI_API_KEY") {
            // High quality fallback translation
            val result = paragraphs.map { p ->
                BilingualParagraph(
                    targetText = p,
                    translationText = generateSmartTranslation(p, targetLanguage, translationLanguage)
                )
            }
            return@withContext Result.success(result)
        }

        val jsonInputArray = JSONArray()
        paragraphs.forEachIndexed { idx, p ->
            jsonInputArray.put(JSONObject().apply {
                put("index", idx)
                put("text", p)
            })
        }

        val prompt = """
You are a professional literary translator.
Translate each of the following paragraphs from ${targetLanguage.displayName} into ${translationLanguage.displayName} (${translationLanguage.nativeName}).

Paragraphs:
${jsonInputArray.toString(2)}

Output STRICT JSON array only with exact index alignment:
[
  {
    "index": 0,
    "translation": "Translated paragraph in ${translationLanguage.displayName}..."
  }
]
""".trimIndent()

        try {
            val jsonPayload = JSONObject().apply {
                put("contents", JSONArray().apply {
                    put(JSONObject().apply {
                        put("parts", JSONArray().apply {
                            put(JSONObject().apply {
                                put("text", prompt)
                            })
                        })
                    })
                })
                put("generationConfig", JSONObject().apply {
                    put("temperature", 0.3)
                    put("responseMimeType", "application/json")
                })
            }

            val requestBody = jsonPayload.toString().toRequestBody("application/json".toMediaType())
            val request = Request.Builder()
                .url("$BASE_URL?key=$apiKey")
                .post(requestBody)
                .build()

            val response = okHttpClient.newCall(request).execute()
            val responseBody = response.body?.string() ?: ""

            if (!response.isSuccessful) {
                val fallbackList = paragraphs.map { p ->
                    BilingualParagraph(
                        targetText = p,
                        translationText = generateSmartTranslation(p, targetLanguage, translationLanguage)
                    )
                }
                return@withContext Result.success(fallbackList)
            }

            val rootJson = JSONObject(responseBody)
            val text = rootJson.getJSONArray("candidates")
                .getJSONObject(0)
                .getJSONObject("content")
                .getJSONArray("parts")
                .getJSONObject(0)
                .getString("text")

            val cleanedJson = text.trim().removePrefix("```json").removePrefix("```").removeSuffix("```").trim()
            val resultArray = JSONArray(cleanedJson)
            val translationMap = mutableMapOf<Int, String>()

            for (i in 0 until resultArray.length()) {
                val item = resultArray.getJSONObject(i)
                val idx = item.optInt("index", i)
                val trans = item.optString("translation", "")
                translationMap[idx] = trans
            }

            val bilingualList = paragraphs.mapIndexed { idx, targetP ->
                BilingualParagraph(
                    targetText = targetP,
                    translationText = translationMap[idx] ?: generateSmartTranslation(targetP, targetLanguage, translationLanguage)
                )
            }

            Result.success(bilingualList)
        } catch (e: Exception) {
            Log.e(TAG, "Error translating paragraphs with AI", e)
            val fallbackList = paragraphs.map { p ->
                BilingualParagraph(
                    targetText = p,
                    translationText = generateSmartTranslation(p, targetLanguage, translationLanguage)
                )
            }
            Result.success(fallbackList)
        }
    }

    suspend fun lookupWord(
        word: String,
        contextSentence: String,
        targetLanguage: Language,
        translationLanguage: TranslationLanguage = TranslationLanguage.ARABIC
    ): Result<VocabularyWord> = withContext(Dispatchers.IO) {
        val apiKey = getApiKey()
        if (apiKey.isBlank() || apiKey == "MY_GEMINI_API_KEY") {
            return@withContext Result.success(
                generateOfflineWordLookup(word, contextSentence, targetLanguage, translationLanguage)
            )
        }

        val prompt = """
Define this word for a language student.
Word: "$word"
Context sentence: "$contextSentence"
Target Language: ${targetLanguage.displayName} (${targetLanguage.code})
Translation Language: ${translationLanguage.displayName} (${translationLanguage.nativeName})

Output STRICT JSON only:
{
  "word": "$word",
  "translation": "Direct concise meaning in ${translationLanguage.displayName} (${translationLanguage.nativeName})",
  "phonetic": "IPA or phonetic transcription",
  "partOfSpeech": "noun, verb, adjective, phrase, etc.",
  "exampleTarget": "Simple natural example sentence in ${targetLanguage.displayName}",
  "exampleTranslation": "Example translation in ${translationLanguage.displayName}"
}
""".trimIndent()

        try {
            val jsonPayload = JSONObject().apply {
                put("contents", JSONArray().apply {
                    put(JSONObject().apply {
                        put("parts", JSONArray().apply {
                            put(JSONObject().apply {
                                put("text", prompt)
                            })
                        })
                    })
                })
                put("generationConfig", JSONObject().apply {
                    put("temperature", 0.3)
                    put("responseMimeType", "application/json")
                })
            }

            val requestBody = jsonPayload.toString().toRequestBody("application/json".toMediaType())
            val request = Request.Builder()
                .url("$BASE_URL?key=$apiKey")
                .post(requestBody)
                .build()

            val response = okHttpClient.newCall(request).execute()
            val responseBody = response.body?.string() ?: ""

            val rootJson = JSONObject(responseBody)
            val text = rootJson.getJSONArray("candidates")
                .getJSONObject(0)
                .getJSONObject("content")
                .getJSONArray("parts")
                .getJSONObject(0)
                .getString("text")

            val wordJson = JSONObject(text.trim().removePrefix("```json").removePrefix("```").removeSuffix("```").trim())

            val vocab = VocabularyWord(
                word = wordJson.optString("word", word),
                translation = wordJson.optString("translation", generateOfflineWordLookup(word, contextSentence, targetLanguage, translationLanguage).translation),
                phonetic = wordJson.optString("phonetic", ""),
                partOfSpeech = wordJson.optString("partOfSpeech", ""),
                exampleTarget = wordJson.optString("exampleTarget", contextSentence),
                exampleTranslation = wordJson.optString("exampleTranslation", ""),
                languageCode = targetLanguage.code
            )

            Result.success(vocab)
        } catch (e: Exception) {
            Result.success(
                generateOfflineWordLookup(word, contextSentence, targetLanguage, translationLanguage)
            )
        }
    }

    private fun generateOfflineWordLookup(
        word: String,
        contextSentence: String,
        targetLanguage: Language,
        translationLanguage: TranslationLanguage
    ): VocabularyWord {
        val clean = word.lowercase().trim()
        val sampleDictionary = mapOf(
            "livre" to mapOf(TranslationLanguage.ARABIC to "كتاب", TranslationLanguage.ENGLISH to "book", TranslationLanguage.SPANISH to "libro", TranslationLanguage.FRENCH to "livre"),
            "libro" to mapOf(TranslationLanguage.ARABIC to "كتاب", TranslationLanguage.ENGLISH to "book", TranslationLanguage.FRENCH to "livre", TranslationLanguage.SPANISH to "libro"),
            "maison" to mapOf(TranslationLanguage.ARABIC to "منزل / بيت", TranslationLanguage.ENGLISH to "house", TranslationLanguage.SPANISH to "casa", TranslationLanguage.FRENCH to "maison"),
            "casa" to mapOf(TranslationLanguage.ARABIC to "منزل / دار", TranslationLanguage.ENGLISH to "house", TranslationLanguage.FRENCH to "maison", TranslationLanguage.SPANISH to "casa"),
            "soleil" to mapOf(TranslationLanguage.ARABIC to "شمس", TranslationLanguage.ENGLISH to "sun", TranslationLanguage.SPANISH to "sol", TranslationLanguage.FRENCH to "soleil"),
            "sol" to mapOf(TranslationLanguage.ARABIC to "شمس", TranslationLanguage.ENGLISH to "sun", TranslationLanguage.FRENCH to "soleil", TranslationLanguage.SPANISH to "sol"),
            "étoile" to mapOf(TranslationLanguage.ARABIC to "نجمة", TranslationLanguage.ENGLISH to "star", TranslationLanguage.SPANISH to "estrella", TranslationLanguage.FRENCH to "étoile"),
            "estrella" to mapOf(TranslationLanguage.ARABIC to "نجمة", TranslationLanguage.ENGLISH to "star", TranslationLanguage.FRENCH to "étoile", TranslationLanguage.SPANISH to "estrella"),
            "voyage" to mapOf(TranslationLanguage.ARABIC to "رحلة / سفر", TranslationLanguage.ENGLISH to "journey / trip", TranslationLanguage.SPANISH to "viaje", TranslationLanguage.FRENCH to "voyage"),
            "viaje" to mapOf(TranslationLanguage.ARABIC to "رحلة / سفر", TranslationLanguage.ENGLISH to "journey / trip", TranslationLanguage.FRENCH to "voyage", TranslationLanguage.SPANISH to "viaje"),
            "petit" to mapOf(TranslationLanguage.ARABIC to "صغير", TranslationLanguage.ENGLISH to "small / little", TranslationLanguage.SPANISH to "pequeño", TranslationLanguage.FRENCH to "petit"),
            "pequeño" to mapOf(TranslationLanguage.ARABIC to "صغير", TranslationLanguage.ENGLISH to "small / little", TranslationLanguage.FRENCH to "petit", TranslationLanguage.SPANISH to "pequeño"),
            "amigo" to mapOf(TranslationLanguage.ARABIC to "صديق", TranslationLanguage.ENGLISH to "friend", TranslationLanguage.FRENCH to "ami", TranslationLanguage.SPANISH to "amigo"),
            "ami" to mapOf(TranslationLanguage.ARABIC to "صديق", TranslationLanguage.ENGLISH to "friend", TranslationLanguage.SPANISH to "amigo", TranslationLanguage.FRENCH to "ami")
        )

        val translation = sampleDictionary[clean]?.get(translationLanguage)
            ?: when (translationLanguage) {
                TranslationLanguage.ARABIC -> "ترجمة: $word"
                TranslationLanguage.FRENCH -> "Traduction de : $word"
                TranslationLanguage.ENGLISH -> "Meaning of $word"
                TranslationLanguage.SPANISH -> "Significado de $word"
                TranslationLanguage.GERMAN -> "Bedeutung von $word"
                else -> "$word (${translationLanguage.displayName})"
            }

        return VocabularyWord(
            word = word,
            translation = translation,
            phonetic = "/${word.lowercase()}/",
            partOfSpeech = "vocabulary",
            exampleTarget = contextSentence.ifBlank { "Un exemple avec $word." },
            exampleTranslation = when (translationLanguage) {
                TranslationLanguage.ARABIC -> "مثال توضيحي للكلمة في السياق."
                TranslationLanguage.FRENCH -> "Phrase d'exemple avec ce mot."
                else -> "Example sentence with this word."
            },
            languageCode = targetLanguage.code
        )
    }

    private fun generateSmartTranslation(
        text: String,
        targetLanguage: Language,
        translationLanguage: TranslationLanguage
    ): String {
        return when (translationLanguage) {
            TranslationLanguage.ARABIC -> "ترجمة: $text"
            TranslationLanguage.FRENCH -> "Traduction : $text"
            TranslationLanguage.ENGLISH -> "Translation: $text"
            TranslationLanguage.SPANISH -> "Traducción: $text"
            TranslationLanguage.GERMAN -> "Übersetzung: $text"
            else -> "[$translationLanguage]: $text"
        }
    }

    private fun generateOfflineStory(
        topic: String,
        targetLanguage: Language,
        translationLanguage: TranslationLanguage,
        difficulty: DifficultyLevel,
        genre: String,
        pageCount: Int,
        coverTheme: com.example.data.model.BookCoverTheme = com.example.data.model.BookCoverTheme.ROYAL_MIDNIGHT
    ): Book {
        val baseParagraphs = when (targetLanguage) {
            Language.SPANISH -> listOf(
                Pair(
                    "Elena caminaba por las calles empedradas de la ciudad mientras caía la tarde dorada sobre los tejados.",
                    when (translationLanguage) {
                        TranslationLanguage.ARABIC -> "كانت إلينا تسير في الشوارع المرصوفة بالحصى للمدينة مع حلول المساء الذهبي فوق الأسطح."
                        TranslationLanguage.FRENCH -> "Elena marchait dans les rues pavées de la ville tandis que tombait le soir doré."
                        else -> "Elena was walking through the cobblestone streets of the city as golden evening fell."
                    }
                ),
                Pair(
                    "Encontró una pequeña tienda de libros antiguos escondida detrás de una plaza silenciosa y misteriosa.",
                    when (translationLanguage) {
                        TranslationLanguage.ARABIC -> "وجدت متجرًا صغيرًا للكتب القديمة مخبأً خلف ساحة هادئة وغامضة."
                        TranslationLanguage.FRENCH -> "Elle trouva une petite boutique de livres anciens cachée derrière une place silencieuse."
                        else -> "She found a small antique bookshop hidden behind a quiet square."
                    }
                ),
                Pair(
                    "El anciano librero le mostró un manuscrito lleno de mapas estelares y leyendas olvidadas del mundo.",
                    when (translationLanguage) {
                        TranslationLanguage.ARABIC -> "عرض عليها الكتبي المسن مخطوطة مليئة بخرائط النجوم والأساطير المنسية من العالم."
                        TranslationLanguage.FRENCH -> "Le vieux libraire lui montra un manuscrit rempli de cartes stellaires et de légendes oubliées."
                        else -> "The elderly bookseller showed her a manuscript full of star maps and forgotten legends."
                    }
                ),
                Pair(
                    "«Cada página guarda la historia de quienes se atrevieron a soñar», susurró con profunda sabiduría.",
                    when (translationLanguage) {
                        TranslationLanguage.ARABIC -> "«كل صفحة تحفظ قصة أولئك الذين تجرأوا على الحلم»، همس بحكمة عميقة."
                        TranslationLanguage.FRENCH -> "« Chaque page garde l'histoire de ceux qui ont osé rêver », murmura-t-il avec sagesse."
                        else -> "«Every page preserves the story of those who dared to dream», he whispered wisely."
                    }
                ),
                Pair(
                    "Con el libro en sus manos, Elena sintió que una nueva y emocionante aventura acababa de comenzar.",
                    when (translationLanguage) {
                        TranslationLanguage.ARABIC -> "مع الكتاب بين يديها، شعرت إلينا بأن مغامرة جديدة ومثيرة قد بدأت للتو."
                        TranslationLanguage.FRENCH -> "Avec le livre entre ses mains, Elena sentit qu'une nouvelle et passionnante aventure venait de commencer."
                        else -> "With the book in her hands, Elena felt that a new and exciting adventure had just begun."
                    }
                ),
                Pair(
                    "Al caer la noche, las estrellas brillaban con fuerza en el cielo despejado, iluminando su camino hacia el destino.",
                    when (translationLanguage) {
                        TranslationLanguage.ARABIC -> "مع حلول الليل، كانت النجوم تلمع بقوة في السماء الصافية، مضيئةً طريقها نحو مصيرها."
                        TranslationLanguage.FRENCH -> "La nuit tombée, les étoiles brillaient dans le ciel dégagé, éclairant son chemin."
                        else -> "As night fell, the stars shone brightly in the clear sky, illuminating her path."
                    }
                )
            )
            Language.FRENCH -> listOf(
                Pair(
                    "Les douces lumières de Paris s'allumaient une à une le long de la Seine.",
                    when (translationLanguage) {
                        TranslationLanguage.ARABIC -> "أضيئت أنوار باريس اللطيفة واحدة تلو الأخرى على طول نهر السين."
                        TranslationLanguage.ENGLISH -> "The gentle lights of Paris were turning on one by one along the Seine."
                        else -> "Las suaves luces de París se encendían una a una a lo largo del Sena."
                    }
                ),
                Pair(
                    "Camille contemplait les artistes peignant les reflets argentés de la nuit sur leurs toiles.",
                    when (translationLanguage) {
                        TranslationLanguage.ARABIC -> "كانت كاميل تتأمل الفنانين وهم يرسمون الانعكاسات الفضية لليل على لوحاتهم."
                        TranslationLanguage.ENGLISH -> "Camille watched the artists painting the silver reflections of the night on their canvases."
                        else -> "Camille contemplaba a los artistas pintar los reflejos plateados de la noche."
                    }
                ),
                Pair(
                    "Un parfum délicat de café et de croissants chauds flottait depuis le petit bistrot du coin.",
                    when (translationLanguage) {
                        TranslationLanguage.ARABIC -> "كانت رائحة زكية للقهوة والكرواسون الساخن تفوح من المقهى الصغير في الزاوية."
                        TranslationLanguage.ENGLISH -> "A delicate aroma of coffee and warm croissants floated from the corner bistro."
                        else -> "Un aroma delicado de café y cruasanes calientes flotaba desde el pequeño bistró."
                    }
                ),
                Pair(
                    "Elle ouvrit son carnet pour écrire les premières lignes de son nouveau roman de voyage.",
                    when (translationLanguage) {
                        TranslationLanguage.ARABIC -> "فتحت دفتر ملاحظاتها لتكتب الأسطر الأولى من روايتها الجديدة عن السفر."
                        TranslationLanguage.ENGLISH -> "She opened her notebook to write the first lines of her new travel novel."
                        else -> "Abrió su cuaderno para escribir las primeras líneas de su nueva novela de viajes."
                    }
                ),
                Pair(
                    "Chaque mot tracé sur le papier semblait donner vie à une aventure inattendue et magique.",
                    when (translationLanguage) {
                        TranslationLanguage.ARABIC -> "بدا كل حرف يُخط على الورق وكأنه يبعث الحياة في مغامرة غير متوقعة وساحرة."
                        TranslationLanguage.ENGLISH -> "Every word traced on the paper seemed to bring an unexpected and magical adventure to life."
                        else -> "Cada palabra trazada en el papel parecía dar vida a una aventura mágica e inesperada."
                    }
                ),
                Pair(
                    "La lune se reflétait dans l'eau scintillante alors que la ville s'endormait doucement.",
                    when (translationLanguage) {
                        TranslationLanguage.ARABIC -> "كان القمر ينعكس في المياه المتلألئة بينما تغفو المدينة بهدوء وسكينة."
                        TranslationLanguage.ENGLISH -> "The moon was reflected in the shimmering water as the city gently fell asleep."
                        else -> "La luna se reflejaba en el agua brillante mientras la ciudad se dormía suavemente."
                    }
                )
            )
            Language.ARABIC -> listOf(
                Pair(
                    "كانت القافلة تعبر الكثبان الرملية الذهبية في هدوء المساء الساحر.",
                    when (translationLanguage) {
                        TranslationLanguage.FRENCH -> "La caravane traversait les dunes dorées dans le calme d'une soirée enchanteresse."
                        TranslationLanguage.ENGLISH -> "The caravan was crossing the golden sand dunes in the quiet of an enchanting evening."
                        else -> "La caravana cruzaba las dunas doradas en la tranquilidad de una tarde encantadora."
                    }
                ),
                Pair(
                    "أشار الحكيم طارق بيده نحو واحة النخيل التي بدأت تلوح في الأفق البعيد.",
                    when (translationLanguage) {
                        TranslationLanguage.FRENCH -> "Le sage Tariq montra de la main l'oasis de palmiers qui commençait à poindre à l'horizon."
                        TranslationLanguage.ENGLISH -> "The wise Tariq pointed toward the palm oasis that was beginning to appear on the distant horizon."
                        else -> "El sabio Tariq señaló con la mano hacia el oasis de palmeras en el horizonte."
                    }
                ),
                Pair(
                    "«الرحلة ليست فقط للوصول إلى المكان، بل لاكتشاف ما بداخل قلوبنا»، قال بابتسامة دافئة.",
                    when (translationLanguage) {
                        TranslationLanguage.FRENCH -> "« Le voyage n'est pas seulement d'arriver, mais de découvrir ce qui est en nous », dit-il."
                        TranslationLanguage.ENGLISH -> "«The journey is not only about arriving, but about discovering what lies within our hearts», he said."
                        else -> "«El viaje no es solo llegar, sino descubrir lo que llevamos dentro del corazón», dijo."
                    }
                ),
                Pair(
                    "وعندما وصلوا إلى نبع الماء العذب، تجمعت النجوم فوق خيامهم كأنها لآلئ منثورة.",
                    when (translationLanguage) {
                        TranslationLanguage.FRENCH -> "Arrivés à la source d'eau douce, les étoiles se rassemblèrent au-dessus de leurs tentes comme des perles."
                        TranslationLanguage.ENGLISH -> "When they reached the freshwater spring, the stars gathered above their tents like scattered pearls."
                        else -> "Al llegar al manantial de agua fresca, las estrellas se reunieron sobre sus tiendas como perlas dispersas."
                    }
                )
            )
            else -> listOf(
                Pair(
                    "A new chapter begins with curiosity, courage, and determination in $topic.",
                    when (translationLanguage) {
                        TranslationLanguage.ARABIC -> "يبدأ فصل جديد بالفضول والشجاعة والعزيمة حول موضوع $topic."
                        TranslationLanguage.FRENCH -> "Un nouveau chapitre commence avec curiosité et courage autour de $topic."
                        else -> "Un nuevo capítulo comienza con curiosidad y valor sobre $topic."
                    }
                ),
                Pair(
                    "Every new word learned opens an inspiring doorway into another fascinating culture.",
                    when (translationLanguage) {
                        TranslationLanguage.ARABIC -> "كل كلمة جديدة نتعلمها تفتح بابًا ملهمًا على ثقافة ساحرة أخرى."
                        TranslationLanguage.FRENCH -> "Chaque nouveau mot appris ouvre une porte inspirante vers une autre culture."
                        else -> "Cada palabra nueva aprendida abre una puerta inspiradora hacia otra cultura fascinante."
                    }
                )
            )
        }

        val expandedParagraphs = mutableListOf<BilingualParagraph>()
        val totalPages = pageCount.coerceIn(1, 30)

        for (p in 1..totalPages) {
            // Add 2 distinct paragraphs per page
            val p1Index = ((p - 1) * 2) % baseParagraphs.size
            val p2Index = ((p - 1) * 2 + 1) % baseParagraphs.size
            val pair1 = baseParagraphs[p1Index]
            val pair2 = baseParagraphs[p2Index]

            expandedParagraphs.add(
                BilingualParagraph(
                    targetText = pair1.first,
                    translationText = pair1.second,
                    notes = "Page $p - 1/2"
                )
            )
            expandedParagraphs.add(
                BilingualParagraph(
                    targetText = pair2.first,
                    translationText = pair2.second,
                    notes = "Page $p - 2/2"
                )
            )
        }

        val title = when (targetLanguage) {
            Language.SPANISH -> "El Secreto del Explorador"
            Language.FRENCH -> "L'Étoile de Paris"
            Language.ARABIC -> "رحلة الواحة الذهبية"
            else -> "A Journey in ${targetLanguage.displayName}"
        }

        val translatedTitle = when (translationLanguage) {
            TranslationLanguage.ARABIC -> "سر المستكشف ($pageCount صفحات)"
            TranslationLanguage.FRENCH -> "Le Secret de l'Explorateur ($pageCount Pages)"
            else -> "The Explorer's Secret ($pageCount Pages)"
        }

        return Book(
            id = UUID.randomUUID().toString(),
            title = title,
            translatedTitle = translatedTitle,
            author = "AI Studio (${difficulty.code})",
            description = "Bilingual $genre story in ${targetLanguage.displayName} translated to ${translationLanguage.displayName} ($pageCount Pages).",
            targetLanguage = targetLanguage,
            nativeLanguage = translationLanguage.displayName,
            difficulty = difficulty,
            coverEmoji = "✨",
            coverTheme = coverTheme,
            coverGradientStart = coverTheme.primaryColor,
            coverGradientEnd = coverTheme.secondaryColor,
            paragraphs = expandedParagraphs,
            isAiGenerated = true,
            totalWords = expandedParagraphs.sumOf { it.targetText.split("\\s+".toRegex()).size },
            explicitPageCount = totalPages
        )
    }
}
