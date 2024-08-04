package com.github.fric.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class PieChartSegment(
    val value: Float,
    val color: Color
)

fun calculateAngles(segments: List<PieChartSegment>): List<Float> {
    val totalValue = segments.sumOf { it.value.toDouble() }.toFloat()
    return segments.map { segment -> (segment.value / totalValue) * 360f }
}

@Composable
fun PieChart(
    segments: List<PieChartSegment>,
    modifier: Modifier = Modifier
) {
    val angles = calculateAngles(segments)

    Canvas(modifier = modifier) {
        var startAngle = 0f
        angles.forEachIndexed { index, sweepAngle ->
            drawArc(
                color = segments[index].color,
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = true
            )
            startAngle += sweepAngle
        }
    }
}

@Composable
fun PieChartScreen() {
    val segments = listOf(
        PieChartSegment(value = 25f, color = Color.Red),
        PieChartSegment(value = 15f, color = Color.Green),
        PieChartSegment(value = 35f, color = Color.Blue),
        PieChartSegment(value = 25f, color = Color.Magenta)
    )

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        PieChart(
            segments = segments,
            modifier = Modifier.size(200.dp)
        )
    }
}
@Preview()
@Composable
fun PieChartPreview() {
    PieChartScreen()
}