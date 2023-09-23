package com.ilhomsoliev.myfinances.feature.goals.presentation.addGoal.amount

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ilhomsoliev.myfinances.shared.sharedUi.SumTextField

@Composable
fun GoalAmountScreen(
    targetAmount: String,
    symbol: Char,
    onAmountChange: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 22.dp),
            text = "Сумма, которую вы \nхотите накопить",
            style = MaterialTheme.typography.labelLarge.copy(
                color = MaterialTheme.colorScheme.tertiary,
                fontSize = 30.sp,
                lineHeight = 35.sp
            )
        )
        SumTextField(
            modifier = Modifier.fillMaxWidth(),
            sum = targetAmount,
            symbol = symbol,
            onSumChange = {
                onAmountChange(it)
            },
            dateText = "до июня 2024",
            onDateClick = {
                // TODO Launch calendar
            })

    }
}
