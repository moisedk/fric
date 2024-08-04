package com.github.fric.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BarChart(data: List<Float>, modifier: Modifier = Modifier, color: Color) {
    val maxDataValue = data.maxOrNull() ?: 1f
    val barWidth = 30.dp

    Canvas(modifier = modifier) {
        val spaceWidth = 8
        data.forEachIndexed { index, value ->
            drawRect(
                color = color,
                topLeft = Offset(index * barWidth.toPx(), size.height - (value / maxDataValue) * size.height),
                size = Size(barWidth.toPx(), (value / maxDataValue) * size.height)
            )
            if (index < data.size) {
                val nextValue = data[index]
                val lineHeight = maxOf(
                    size.height - (value / maxDataValue) * size.height,
                    size.height - (nextValue / maxDataValue) * size.height
                )
                drawLine(
                    strokeWidth = spaceWidth.dp.toPx(),
                    color = Color.DarkGray,
                    start = Offset(index * barWidth.toPx() + barWidth.toPx(), lineHeight),
                    end = Offset(index * barWidth.toPx() + barWidth.toPx(), size.height)
                )
            }
        }
    }
}

@Composable
fun BarChartScreen(color: Color) {
    val data = listOf(5f, 1f, 15f, 20f, 16f, 9f,10f,12f,16f)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        BarChart(data = data, modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
            color = color

        )
    }
}

@Composable
@Preview(showBackground = false)
fun PreviewBarChartScreen() {
    BarChartScreen(Color.Green)
}
