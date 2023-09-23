package com.ilhomsoliev.myfinances.shared.sharedUi

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomProgressBar(
    modifier: Modifier = Modifier,
    progress: Double,
    height: Dp = 5.dp,
    backgroundColor: Color = Color(0xFFE7E6FE),
    foregroundColor: Color = Color(0xFFFF6666)
) {
    Box(
        modifier = modifier
            .height(height)
            .clip(RoundedCornerShape(30.dp))
            .background(backgroundColor),
        contentAlignment = Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(progress.toFloat())
                .height(height)
                .clip(RoundedCornerShape(30.dp))
                .background(foregroundColor)
        )
    }
}