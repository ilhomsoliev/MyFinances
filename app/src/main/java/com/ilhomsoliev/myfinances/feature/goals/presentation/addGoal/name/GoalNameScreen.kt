package com.ilhomsoliev.myfinances.feature.goals.presentation.addGoal.name

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ilhomsoliev.myfinances.R

@Composable
fun GoalNameScreen(
    goalName: String,
    isReminderActive: Boolean,
    onGoalNameChange: (String) -> Unit,
    onReminderClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.align(Alignment.Start),
            text = "Название цели",
            style = MaterialTheme.typography.labelLarge.copy(
                color = MaterialTheme.colorScheme.tertiary,
                fontSize = 30.sp,
                lineHeight = 35.sp
            ),
        )

        GoalNameTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 22.dp),
            value = goalName,
            onValueChange = {
                onGoalNameChange(it)
            })

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 14.dp)
                .fillMaxWidth()
                .clickable { onReminderClick() },
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Напоминать о цели",
                style = MaterialTheme.typography.labelMedium.copy(color = MaterialTheme.colorScheme.onTertiary)
            )

            IconButton(onClick = {
                onReminderClick()
            }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_check_circle),
                    contentDescription = null,
                    colorFilter = if (isReminderActive) null else ColorFilter.tint(color = MaterialTheme.colorScheme.onTertiary)
                )
            }
        }

    }
}

@Composable
private fun GoalNameTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit
) {

    BasicTextField(value = value, onValueChange = {
        onValueChange(it)
    }, keyboardOptions = KeyboardOptions.Default.copy(
        imeAction = ImeAction.Done,
        keyboardType = KeyboardType.Text,
        capitalization = KeyboardCapitalization.Sentences
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
                    innerTextField()
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