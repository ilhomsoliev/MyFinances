package com.ilhomsoliev.myfinances.feature.goals.presentation.detailed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.ilhomsoliev.myfinances.shared.sharedUi.CustomButton
import com.ilhomsoliev.myfinances.shared.sharedUi.SumTextField

@Composable
internal fun AddRefillDialog(
    isDialogOpen: Boolean,
    value: String,
    onValueChange: (String) -> Unit,
    onSaveClick: () -> Unit,
    symbol: Char,
    onDismiss: () -> Unit
) {
    if (isDialogOpen) {
        Dialog(onDismissRequest = onDismiss) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(30.dp))
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth(0.8f)
                        .padding(top = 18.dp, bottom = 22.dp)
                ) {
                    IconButton(
                        modifier = Modifier.align(Alignment.End),
                        onClick = { onDismiss() }) {
                        Icon(imageVector = Icons.Outlined.Cancel, contentDescription = null)
                    }
                    Text(
                        text = "Пополнение", style = MaterialTheme.typography.labelMedium.copy(
                            fontSize = 26.sp,
                            lineHeight = 42.64.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFFC0C0C0)
                        )
                    )
                    SumTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 28.dp),
                        sum = value,
                        symbol = symbol,
                        onSumChange = {
                            onValueChange(it)
                        },
                        onDateClick = null
                    )
                    CustomButton(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 28.dp),
                        text = "Сохранить"
                    ) {
                        onSaveClick()
                    }
                }
            }
        }
    }
}