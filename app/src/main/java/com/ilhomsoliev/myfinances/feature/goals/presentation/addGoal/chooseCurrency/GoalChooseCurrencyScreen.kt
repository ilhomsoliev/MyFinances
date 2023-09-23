package com.ilhomsoliev.myfinances.feature.goals.presentation.addGoal.chooseCurrency

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ilhomsoliev.myfinances.shared.model.ui.constants.CurrencyModel
import com.ilhomsoliev.myfinances.shared.model.ui.constants.getCurrencies


@Composable
fun GoalChooseCurrencyScreen(
    activeCurrencyModel: CurrencyModel?,
    onCurrencyClick: (CurrencyModel) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            modifier = Modifier.padding(bottom = 22.dp),
            text = "Выберите валюту",
            style = MaterialTheme.typography.labelLarge.copy(color = MaterialTheme.colorScheme.tertiary)
        )
        LazyColumn(content = {
            items(getCurrencies()) {
                CurrencyItem(
                    currencyModel = it,
                    selected = activeCurrencyModel?.id == it.id,
                    onClick = {
                        onCurrencyClick(it)
                    })
            }
        })
    }
}

@Composable
private fun CurrencyItem(
    currencyModel: CurrencyModel,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(all = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .padding(end = 14.dp)
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color.Red), contentAlignment = Alignment.Center
            ) {
                Text(text = currencyModel.symbol.toString(), fontSize = 16.sp, color = Color.White)
            }
            Text(
                text = currencyModel.pluralName,
                style = MaterialTheme.typography.labelMedium.copy(
                    color = MaterialTheme.colorScheme.onTertiary,
                    fontWeight = FontWeight(600)
                )
            )
        }

        Icon(
            imageVector = Icons.Default.Done,
            contentDescription = null,
            modifier = if (!selected) Modifier.size(0.dp) else Modifier,
            tint = MaterialTheme.colorScheme.onTertiary
        )

    }

}
