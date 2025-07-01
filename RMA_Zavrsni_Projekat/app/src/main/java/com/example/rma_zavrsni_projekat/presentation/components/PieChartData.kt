package com.example.rma_zavrsni_projekat.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class PieSlice(val label: String, val value: Float, val color: Color)

@Composable
fun SimplePieData(
    slices: List<PieSlice>,
    modifier: Modifier = Modifier,
    size: Dp = 200.dp,
    strokeWidth: Dp = 40.dp
) {
    val total = slices.sumOf { it.value.toDouble() }.toFloat()

    Column(
        modifier = modifier,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Canvas(modifier = Modifier.size(size)) {
            var startAngle = -90f
            slices.forEach { slice ->
                val sweepAngle = (slice.value / total) * 360f
                drawArc(
                    color = slice.color,
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    useCenter = false,
                    style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Butt)
                )
                startAngle += sweepAngle

            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        slices.forEach { slice ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .background(slice.color, shape = CircleShape)
                )
                Text(
                    text = "${slice.label} (${slice.value.toInt()})",
                    fontSize = 16.sp,
                    color = colorScheme.onBackground
                )
            }
        }
    }
}