package com.ilhomsoliev.myfinances.feature.main.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.ilhomsoliev.myfinances.shared.model.enumeration.TransactionType
import com.ilhomsoliev.myfinances.shared.sharedUi.CustomButton

@Composable
fun AddTransactionDialog(
    isActive: Boolean,
    onTypeClick: (TransactionType) -> Unit,
    onDismiss: () -> Unit,
) {

    if (isActive)
        Dialog(onDismissRequest = {
            onDismiss()
        }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(modifier = Modifier.align(Alignment.End).offset(28.dp), onClick = { onDismiss() }) {
                    Icon(imageVector = Icons.Default.Cancel, contentDescription = null)
                }
                Text(
                    text = "Какую операцию хотите добавить?",
                    style = androidx.compose.material3.MaterialTheme.typography.labelMedium.copy(
                        fontSize = 20.sp,
                        lineHeight = 28.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF45669A),
                    )
                )
                Spacer(modifier = Modifier.height(30.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    CustomButton(
                        modifier = Modifier.weight(1f),
                        text = "Income",
                    ) {
                        onTypeClick(TransactionType.Income)
                    }
                    Spacer(modifier = Modifier.width(14.dp))
                    CustomButton(
                        modifier = Modifier.weight(1f),
                        text = "Outcome",
                    ) {
                        onTypeClick(TransactionType.Outcome)
                    }
                }
            }
        }
}

@Composable
private fun TypeChip(
    modifier: Modifier = Modifier,
    text: String,
    isActive: Boolean,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(15.dp))
            .clickable {
                onClick()
            }, contentAlignment = Alignment.Center
    ) {
        Text(
            text = text, modifier = Modifier.padding(horizontal = 23.dp, vertical = 10.dp),
            color = if (isActive) Color.Red else Color.Green
        )
    }

}