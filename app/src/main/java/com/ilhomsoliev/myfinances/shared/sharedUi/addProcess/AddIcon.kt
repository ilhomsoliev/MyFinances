package com.ilhomsoliev.myfinances.shared.sharedUi.addProcess

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ilhomsoliev.myfinances.R

@Composable
fun AddIcon(
    modifier:Modifier = Modifier,
    onClick: () -> Unit,
) {
    CustomIcon(
        modifier = modifier,
        image = R.drawable.ic_add,
        colors = listOf(Color(0xFF80E0FF), Color(0xFF2F5DFD)),
        onClick = onClick
    )
}

@Composable
fun CustomIcon(
    modifier:Modifier = Modifier,
    image: Int,
    colors: List<Color>,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .size(65.dp)
            .clip(CircleShape)
            .background(Brush.horizontalGradient(colors))
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Image(painter = painterResource(id = image), contentDescription = null)
    }
}