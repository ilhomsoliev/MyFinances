package com.ilhomsoliev.myfinances.feature.accounts.presentation.accounts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.ilhomsoliev.myfinances.shared.sharedUi.CustomButton
import com.ilhomsoliev.myfinances.shared.sharedUi.FTextFieldStringDivider

@Composable
internal fun AddAccountDialog(
    isDialogOpen: Boolean,
    onSaveClick: (String, String, Int) -> Unit,
    onDismiss: () -> Unit
) {
    var accountName by remember { mutableStateOf("") }
    var accountAmount by remember { mutableStateOf("") }
    var chosenIcon by remember { mutableStateOf<Int?>(null) }

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
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Добавить", style = MaterialTheme.typography.labelMedium.copy(
                                fontSize = 30.sp,
                                fontWeight = FontWeight(600),
                                color = Color(0xFF979797),
                            )
                        )
                        IconButton(onClick = {
                            onDismiss()
                        }) {
                            Icon(imageVector = Icons.Default.Cancel, contentDescription = null)
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                    FTextFieldStringDivider(
                        modifier = Modifier.fillMaxWidth(),
                        value = accountName,
                        hint = "Название",
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Text,
                            capitalization = KeyboardCapitalization.Sentences
                        ),
                        keyboardActions = KeyboardActions(onNext = {

                        }),
                        onValueChange = {
                            accountName = it
                        },
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    FTextFieldStringDivider(
                        modifier = Modifier.fillMaxWidth(),
                        value = accountAmount,
                        hint = "Сумма",
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Number,
                            capitalization = KeyboardCapitalization.None
                        ),
                        keyboardActions = KeyboardActions(onNext = {

                        }),
                        onValueChange = {
                            accountAmount = it
                        },
                    )
                    Spacer(modifier = Modifier.height(18.dp))
                    Text(text = "Иконка")
                    // TODO
                    CustomButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 28.dp),
                        text = "Сохранить"
                    ) {
                        chosenIcon = 1 // TODO change
                        chosenIcon?.let { iconId ->
                            onSaveClick(accountName, accountAmount, iconId)
                            accountName = ""
                            accountAmount = ""
                            chosenIcon = null
                        }
                    }
                }
            }
        }
    }
}