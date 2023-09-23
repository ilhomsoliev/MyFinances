package com.ilhomsoliev.myfinances.shared.sharedUi.addProcess

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ilhomsoliev.myfinances.shared.sharedUi.CustomButton
import com.ilhomsoliev.myfinances.shared.sharedUi.CustomProgressBar


@Composable
fun AddProcessButtons(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onSkipClick: (() -> Unit)? = null,
    isActive: Boolean,
    isSkipAvailable: Boolean = false,
    progress: Double
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 52.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomButton(
            text = "Далее", modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(bottom = 14.dp),
            onClick = {
                if (isActive)
                    onClick()
            }
        )
        if (isSkipAvailable)
            Text(
                modifier = Modifier
                    .padding(bottom = 14.dp)
                    .clickable {
                        onSkipClick?.let { it() }
                    }, text = "Пропустить", style = MaterialTheme.typography.labelMedium
            )
        CustomProgressBar(
            modifier = Modifier.fillMaxWidth(0.7f),
            progress = progress,
            height = 10.dp,
            foregroundColor = Color(0xFFBFCAD8),
            backgroundColor = Color.White
        )

    }
}