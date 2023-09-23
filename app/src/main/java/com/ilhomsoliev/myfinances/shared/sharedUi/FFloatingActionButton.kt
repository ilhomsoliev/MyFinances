package com.ilhomsoliev.myfinances.shared.sharedUi

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.ilhomsoliev.myfinances.app.theme.colors.Gradients

@Composable
fun FFloatingActionButton(
    onClick: () -> Unit
) {
    // TODO add shadows
    Box(
        modifier = Modifier
            .defaultMinSize(
                minWidth = 56.dp,
                minHeight = 56.dp,
            )
            .shadow(elevation = 3.dp, shape = CircleShape)
            .clip(CircleShape)
            .background(
                Brush.horizontalGradient(
                    Gradients.blue()
                )
            )

            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center,
    ) {
        Image(
            modifier = Modifier.size(42.dp),
            imageVector = Icons.Default.Add,
            contentDescription = null,
            colorFilter = ColorFilter.tint(Color.White)
        )
    }
}