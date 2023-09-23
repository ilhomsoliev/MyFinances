package com.ilhomsoliev.myfinances.shared.sharedUi

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SumTextField(
    modifier: Modifier = Modifier,
    sum: String,
    symbol: Char,
    dateText: String = "",
    onSumChange: (String) -> Unit,
    onDateClick: (() -> Unit)? = null,
) {
    val openDialog = remember { mutableStateOf(true) }
    //val datePicker = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)
    val state = rememberDatePickerState()

    if (openDialog.value) {
        DatePickerDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                    }
                ) {
                    Text("CANCEL")
                }
            }
        ) {
            DatePicker(
                state = state
            )
        }
    }
    BasicTextField(value = sum, onValueChange = {
        onSumChange(it)
    }, keyboardOptions = KeyboardOptions.Default.copy(
        imeAction = ImeAction.Done,
        keyboardType = KeyboardType.Number,
        capitalization = KeyboardCapitalization.None
    ), textStyle = MaterialTheme.typography.labelLarge, decorationBox = { innerTextField ->
        Column(modifier = modifier) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .height(26.dp)
                            .width(1.dp)
                            .background(MaterialTheme.colorScheme.onTertiary)
                    )
                    Text(
                        modifier = Modifier
                            .padding(end = 10.dp), text = symbol.toString()
                    )
                    innerTextField()
                }
                onDateClick?.let {
                    openDialog.value = true
                    Text(
                        modifier = Modifier.clickable {
                            it.invoke()
                        },
                        text = dateText,
                        style = MaterialTheme.typography.bodyLarge.copy(color = Color(0xFF9B9CF8))
                    )
                }
            }
            Divider(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.onTertiary)
            )
        }
    })

}