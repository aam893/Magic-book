package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Book
import com.example.data.model.BookCoverTheme
import com.example.data.model.DifficultyLevel
import com.example.data.model.Language
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun BookCoverCanvas(
    book: Book,
    modifier: Modifier = Modifier,
    aspectRatio: Float = 0.68f, // Standard luxury book 2:3 ratio
    elevation: Dp = 8.dp,
    showBadges: Boolean = true,
    onClick: (() -> Unit)? = null
) {
    BookCoverCanvas(
        title = book.title,
        translatedTitle = book.translatedTitle,
        author = book.author,
        targetLanguage = book.targetLanguage,
        difficulty = book.difficulty,
        theme = book.coverTheme,
        pageCount = book.totalPagesCount,
        modifier = modifier,
        aspectRatio = aspectRatio,
        elevation = elevation,
        showBadges = showBadges,
        onClick = onClick
    )
}

@Composable
fun BookCoverCanvas(
    title: String,
    translatedTitle: String = "",
    author: String = "AI Polyglot Studio",
    targetLanguage: Language = Language.FRENCH,
    difficulty: DifficultyLevel = DifficultyLevel.B1,
    theme: BookCoverTheme = BookCoverTheme.ROYAL_MIDNIGHT,
    pageCount: Int = 1,
    modifier: Modifier = Modifier,
    aspectRatio: Float = 0.68f,
    elevation: Dp = 8.dp,
    showBadges: Boolean = true,
    onClick: (() -> Unit)? = null
) {
    val primaryColor = Color(theme.primaryColor)
    val secondaryColor = Color(theme.secondaryColor)
    val goldColor = Color(theme.accentGoldColor)

    Box(
        modifier = modifier
            .aspectRatio(aspectRatio)
            .shadow(elevation, shape = RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp, topStart = 4.dp, bottomStart = 4.dp))
            .clip(RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp, topStart = 4.dp, bottomStart = 4.dp))
            .then(if (onClick != null) Modifier.clickable { onClick() } else Modifier)
    ) {
        // 1. Canvas Artistic Background & Ornaments
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // Background Deep Gradient
            drawRect(
                brush = Brush.radialGradient(
                    colors = listOf(secondaryColor, primaryColor, Color(0xFF030712)),
                    center = Offset(w * 0.5f, h * 0.4f),
                    radius = h * 0.8f
                )
            )

            // Book Spine Shading & Highlights on the left edge
            val spineWidth = (w * 0.08f).coerceIn(10f, 26f)
            drawRect(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFF000000).copy(alpha = 0.7f),
                        Color(0xFFFFFFFF).copy(alpha = 0.18f),
                        Color(0xFF000000).copy(alpha = 0.5f),
                        Color.Transparent
                    ),
                    startX = 0f,
                    endX = spineWidth * 1.6f
                ),
                size = Size(spineWidth * 1.6f, h)
            )

            // Spine Crease Lines
            drawLine(
                color = goldColor.copy(alpha = 0.4f),
                start = Offset(spineWidth, 0f),
                end = Offset(spineWidth, h),
                strokeWidth = 1.5f
            )

            // Outer Gold Frame with Filigree Corners
            val margin = w * 0.07f
            val frameRect = Rect(margin + spineWidth * 0.6f, margin, w - margin, h - margin)

            // Double Gold Border
            drawRect(
                color = goldColor.copy(alpha = 0.65f),
                topLeft = Offset(frameRect.left, frameRect.top),
                size = Size(frameRect.width, frameRect.height),
                style = Stroke(width = 2f)
            )

            val innerMargin = margin + 5f
            drawRect(
                color = goldColor.copy(alpha = 0.35f),
                topLeft = Offset(innerMargin + spineWidth * 0.6f, innerMargin),
                size = Size(w - innerMargin * 2 - spineWidth * 0.6f, h - innerMargin * 2),
                style = Stroke(
                    width = 1f,
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(6f, 4f), 0f)
                )
            )

            // Corner Ornaments
            drawCornerFiligree(goldColor, frameRect.left, frameRect.top, 1, 1)
            drawCornerFiligree(goldColor, frameRect.right, frameRect.top, -1, 1)
            drawCornerFiligree(goldColor, frameRect.left, frameRect.bottom, 1, -1)
            drawCornerFiligree(goldColor, frameRect.right, frameRect.bottom, -1, -1)

            // Center Geometric Medallion / Celestial Pattern
            drawCentralMedallion(goldColor, w * 0.52f, h * 0.46f, w * 0.28f, theme.patternType)

            // Bottom Ribbon Bookmark Accent
            val ribbonPath = Path().apply {
                val rx = w * 0.82f
                val rw = w * 0.09f
                moveTo(rx, 0f)
                lineTo(rx + rw, 0f)
                lineTo(rx + rw, h * 0.18f)
                lineTo(rx + rw / 2f, h * 0.14f)
                lineTo(rx, h * 0.18f)
                close()
            }
            drawPath(
                path = ribbonPath,
                color = goldColor.copy(alpha = 0.85f)
            )
        }

        // 2. High-Quality Editorial Typography & Badges
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 18.dp, end = 14.dp, top = 14.dp, bottom = 12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Top Header: Target Language Emblem & Level Seal
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = Color(0xFF000000).copy(alpha = 0.5f),
                    border = androidx.compose.foundation.BorderStroke(1.dp, goldColor.copy(alpha = 0.6f))
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 7.dp, vertical = 3.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${targetLanguage.flag} ${targetLanguage.displayName.uppercase()}",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 10.sp,
                            letterSpacing = 1.sp
                        )
                    }
                }

                if (showBadges) {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = Color(difficulty.colorHex).copy(alpha = 0.85f)
                    ) {
                        Text(
                            text = difficulty.code,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White,
                            fontSize = 10.sp,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
            }

            // Center: Book Title and Subtitle
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Gold decorative star
                Text(
                    text = "✦  ✧  ✦",
                    color = goldColor.copy(alpha = 0.85f),
                    fontSize = 11.sp,
                    letterSpacing = 3.sp
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFFFFFBEB),
                    textAlign = TextAlign.Center,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 22.sp,
                    fontSize = if (title.length > 28) 16.sp else 18.sp
                )

                if (translatedTitle.isNotBlank() && translatedTitle != title) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = translatedTitle,
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic,
                        color = goldColor.copy(alpha = 0.9f),
                        textAlign = TextAlign.Center,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 12.sp
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                // Page Count Emblem
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Color.White.copy(alpha = 0.12f),
                    border = androidx.compose.foundation.BorderStroke(0.5.dp, goldColor.copy(alpha = 0.4f))
                ) {
                    Text(
                        text = "📖 $pageCount ${if (pageCount == 1) "Page" else "Pages"}",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFFFDE68A),
                        fontSize = 10.sp,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                    )
                }
            }

            // Bottom: Author & Luxury Footer
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = author.uppercase(),
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White.copy(alpha = 0.75f),
                    fontSize = 9.sp,
                    letterSpacing = 1.5.sp,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(2.dp))
                Box(
                    modifier = Modifier
                        .width(40.dp)
                        .height(1.dp)
                        .background(goldColor.copy(alpha = 0.6f))
                )
            }
        }
    }
}

private fun DrawScope.drawCornerFiligree(color: Color, cx: Float, cy: Float, dirX: Int, dirY: Int) {
    val size = 16f
    val path = Path().apply {
        moveTo(cx, cy + dirY * size)
        lineTo(cx, cy)
        lineTo(cx + dirX * size, cy)
        moveTo(cx + dirX * (size * 0.4f), cy + dirY * (size * 0.4f))
        lineTo(cx + dirX * size, cy + dirY * size)
    }
    drawPath(path = path, color = color.copy(alpha = 0.6f), style = Stroke(width = 1.5f))
}

private fun DrawScope.drawCentralMedallion(color: Color, cx: Float, cy: Float, radius: Float, patternType: String) {
    // Outer dashed ring
    drawCircle(
        color = color.copy(alpha = 0.18f),
        radius = radius,
        center = Offset(cx, cy),
        style = Stroke(width = 1.5f, pathEffect = PathEffect.dashPathEffect(floatArrayOf(4f, 4f), 0f))
    )

    // Inner subtle ring
    drawCircle(
        color = color.copy(alpha = 0.12f),
        radius = radius * 0.7f,
        center = Offset(cx, cy),
        style = Stroke(width = 1f)
    )

    // 8-Point Star or Geometric Rays
    val points = 8
    val r1 = radius * 0.85f
    val r2 = radius * 0.4f

    val starPath = Path()
    for (i in 0 until points * 2) {
        val angle = (i * PI / points).toFloat()
        val r = if (i % 2 == 0) r1 else r2
        val px = cx + r * cos(angle)
        val py = cy + r * sin(angle)
        if (i == 0) starPath.moveTo(px, py) else starPath.lineTo(px, py)
    }
    starPath.close()

    drawPath(
        path = starPath,
        color = color.copy(alpha = 0.08f)
    )
    drawPath(
        path = starPath,
        color = color.copy(alpha = 0.25f),
        style = Stroke(width = 1f)
    )
}
