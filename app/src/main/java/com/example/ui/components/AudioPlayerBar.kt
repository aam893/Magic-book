package com.example.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AudioPlayerBar(
    isPlaying: Boolean,
    currentPage: Int,
    totalPages: Int,
    speechRate: Float,
    targetLanguageName: String,
    onTogglePlayPause: () -> Unit,
    onPreviousPage: () -> Unit,
    onNextPage: () -> Unit,
    onReplayCurrentPage: () -> Unit,
    onSetSpeechRate: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    var speedMenuExpanded by remember { mutableStateOf(false) }
    val speedOptions = listOf(0.75f, 0.9f, 1.0f, 1.15f, 1.25f, 1.5f)

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.95f),
        tonalElevation = 8.dp,
        shadowElevation = 12.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 14.dp)
        ) {
            // Top Bar: Page status + Audio Waveform + Speed
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Page Indicator
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(if (isPlaying) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Page $currentPage of $totalPages",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                // Waveform animation when playing
                if (isPlaying) {
                    AudioWaveformVisualizer(
                        modifier = Modifier
                            .width(60.dp)
                            .height(20.dp)
                    )
                } else {
                    Text(
                        text = "Auto-Continuous",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                // Speech Rate Controller
                Box {
                    Surface(
                        onClick = { speedMenuExpanded = true },
                        shape = RoundedCornerShape(10.dp),
                        color = MaterialTheme.colorScheme.primaryContainer,
                        modifier = Modifier.testTag("audio_speed_button")
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Default.Speed,
                                contentDescription = null,
                                modifier = Modifier.size(14.dp),
                                tint = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "${speechRate}x",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }

                    DropdownMenu(
                        expanded = speedMenuExpanded,
                        onDismissRequest = { speedMenuExpanded = false }
                    ) {
                        Text(
                            "Speech Narration Speed:",
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        speedOptions.forEach { rate ->
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = "${rate}x ${if (rate == 1.0f) "(Normal)" else if (rate < 1.0f) "(Slower)" else "(Faster)"}",
                                        fontWeight = if (rate == speechRate) FontWeight.Bold else FontWeight.Normal
                                    )
                                },
                                onClick = {
                                    onSetSpeechRate(rate)
                                    speedMenuExpanded = false
                                }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Reading Progress Line
            LinearProgressIndicator(
                progress = {
                    if (totalPages > 0) (currentPage.toFloat() / totalPages.toFloat()).coerceIn(0f, 1f) else 0f
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .clip(RoundedCornerShape(2.dp)),
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f)
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Player Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Replay Current Page
                IconButton(
                    onClick = onReplayCurrentPage,
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        Icons.Default.Replay,
                        contentDescription = "Replay Page",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                // Previous Page Button
                IconButton(
                    onClick = onPreviousPage,
                    enabled = currentPage > 1,
                    modifier = Modifier.size(44.dp)
                ) {
                    Icon(
                        Icons.Default.SkipPrevious,
                        contentDescription = "Previous Page",
                        tint = if (currentPage > 1) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.outlineVariant,
                        modifier = Modifier.size(28.dp)
                    )
                }

                // Main Continuous Play / Pause Button
                Surface(
                    onClick = onTogglePlayPause,
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primary,
                    shadowElevation = 6.dp,
                    modifier = Modifier
                        .size(56.dp)
                        .testTag("continuous_play_pause_button")
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                            contentDescription = if (isPlaying) "Pause Continuous Reading" else "Play Continuous Reading",
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }

                // Next Page Button
                IconButton(
                    onClick = onNextPage,
                    enabled = currentPage < totalPages,
                    modifier = Modifier.size(44.dp)
                ) {
                    Icon(
                        Icons.Default.SkipNext,
                        contentDescription = "Next Page",
                        tint = if (currentPage < totalPages) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.outlineVariant,
                        modifier = Modifier.size(28.dp)
                    )
                }

                // Language voice indicator
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.6f)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.VolumeUp,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = targetLanguageName.take(3).uppercase(),
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            fontSize = 10.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AudioWaveformVisualizer(
    modifier: Modifier = Modifier,
    barCount: Int = 5,
    color: Color = MaterialTheme.colorScheme.primary
) {
    val infiniteTransition = rememberInfiniteTransition(label = "audio_waveform")

    val anim1 by infiniteTransition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(tween(350, easing = FastOutSlowInEasing), RepeatMode.Reverse),
        label = "bar1"
    )
    val anim2 by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 0.3f,
        animationSpec = infiniteRepeatable(tween(420, easing = FastOutSlowInEasing), RepeatMode.Reverse),
        label = "bar2"
    )
    val anim3 by infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(tween(300, easing = FastOutSlowInEasing), RepeatMode.Reverse),
        label = "bar3"
    )
    val anim4 by infiniteTransition.animateFloat(
        initialValue = 0.9f,
        targetValue = 0.2f,
        animationSpec = infiniteRepeatable(tween(480, easing = FastOutSlowInEasing), RepeatMode.Reverse),
        label = "bar4"
    )
    val anim5 by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.85f,
        animationSpec = infiniteRepeatable(tween(380, easing = FastOutSlowInEasing), RepeatMode.Reverse),
        label = "bar5"
    )

    val anims = listOf(anim1, anim2, anim3, anim4, anim5)

    Canvas(modifier = modifier) {
        val totalWidth = size.width
        val maxHeight = size.height
        val barWidth = (totalWidth / (barCount * 2f))
        val spacing = barWidth

        anims.take(barCount).forEachIndexed { index, animFraction ->
            val barHeight = (maxHeight * animFraction).coerceIn(4f, maxHeight)
            val left = index * (barWidth + spacing) + spacing / 2f
            val top = (maxHeight - barHeight) / 2f

            drawRoundRect(
                color = color,
                topLeft = Offset(left, top),
                size = Size(barWidth, barHeight),
                cornerRadius = CornerRadius(barWidth / 2f, barWidth / 2f)
            )
        }
    }
}
