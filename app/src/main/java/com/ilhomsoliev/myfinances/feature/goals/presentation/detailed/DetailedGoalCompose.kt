package com.ilhomsoliev.myfinances.feature.goals.presentation.detailed

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ilhomsoliev.myfinances.R
import com.ilhomsoliev.myfinances.core.date.LocalDateTime
import com.ilhomsoliev.myfinances.core.date.displayDate
import com.ilhomsoliev.myfinances.shared.model.enumeration.goal.RefillType
import com.ilhomsoliev.myfinances.shared.model.ui.goal.GoalUi
import com.ilhomsoliev.myfinances.shared.model.ui.goal.RefillUi
import com.ilhomsoliev.myfinances.shared.sharedUi.CustomProgressBar
import com.ilhomsoliev.myfinances.shared.sharedUi.FFloatingActionButton
import com.ilhomsoliev.myfinances.shared.sharedUi.ImageBox

data class DetailedGoalState(
    val goal: GoalUi?,
    val refiils: List<RefillUi> = emptyList()
)

interface DetailedGoalCallback {
    fun onBack()
    fun onSettings()
    fun onSaveClick(sum: String)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailedGoalContent(
    state: DetailedGoalState,
    callback: DetailedGoalCallback
) {
    var isDialogOpen by remember { mutableStateOf(false) }
    var sum by remember { mutableStateOf("") }

    state.goal?.let { goal ->
        val currency = goal.currency
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            topBar = {
                TopAppBar(onBackClick = {
                    callback.onBack()
                }, onSettingsClick = {
                    callback.onSettings()
                }, goalName = goal.name)
            },
            floatingActionButton = {
                FFloatingActionButton {
                    isDialogOpen = true
                }
            }
        ) { padding ->
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .padding(horizontal = 16.dp)
            ) {
                if (!goal.imageUrl.isNullOrEmpty()) {
                    item {
                        ImageBox(
                            modifier = Modifier
                                .height(220.dp)
                                .fillMaxWidth(),
                            imageHttp = goal.imageUrl
                        ) {
                            Box(
                                modifier = Modifier
                                    .height(220.dp)
                                    .fillMaxWidth()
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                        Spacer(modifier = Modifier.height(21.dp))
                    }
                }
                item {
                    Text(
                        modifier = Modifier.padding(top = 21.dp),
                        text = "Накоплено : ${goal.currentAmount} ${currency.symbol}",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontSize = 22.sp,
                            lineHeight = 22.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF979797),
                        )
                    )
                }
                item {
                    Text(
                        modifier = Modifier.padding(top = 12.dp),
                        text = "из ${goal.targetAmount} ${currency.symbol} до ${LocalDateTime(goal.deadline).displayDate()} г.",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontSize = 16.sp,
                            lineHeight = 22.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF979797),
                        )
                    ) // 16 июня 2025 г
                }
                item {
                    CustomProgressBar(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp),
                        progress = goal.currentAmount / goal.targetAmount,
                        height = 10.dp,
                        backgroundColor = Color(0xFFC0C0C0),
                        foregroundColor = Color(0xFFFF6666)
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                }

                items(state.refiils) {
                    RefillItem(refill = it)
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
        AddRefillDialog(
            isDialogOpen = isDialogOpen,
            value = sum,
            onValueChange = {
                sum = it
            },
            onSaveClick = {
                callback.onSaveClick(sum)
                sum = ""
                isDialogOpen = false
            },
            symbol = currency.symbol,
            onDismiss = {
                isDialogOpen = false
            },
        )
    }
}

@Composable
private fun RefillItem(
    refill: RefillUi
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(Color.White) // TODO
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = if (refill.type == RefillType.Add) "Пополнение" else "Снятие",
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontSize = 14.25.sp,
                        lineHeight = 23.36.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF45669A),
                    )
                )
                Text(
                    text = LocalDateTime(refill.deadline).displayDate(),
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontSize = 11.87.sp,
                        lineHeight = 19.47.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF45669A),
                    )
                )
            }
            Text(
                text = "${refill.amount} ${refill.currency.symbol}",
                style = MaterialTheme.typography.labelMedium.copy(
                    fontSize = 20.sp,
                    lineHeight = 32.8.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFFFF6666),
                )
            )

        }
    }

}

@Composable
private fun TopAppBar(
    onBackClick: () -> Unit,
    onSettingsClick: () -> Unit,
    goalName: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = { onBackClick() }) {
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_left),
                contentDescription = null
            )
        }

        Text(
            text = goalName,
            style = MaterialTheme.typography.labelLarge.copy(
                fontSize = 18.sp,
                lineHeight = 22.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF0B2D74)
            )
        )

        IconButton(onClick = { onSettingsClick() }) {
            Image(
                painter = painterResource(id = R.drawable.ic_settings_gear),
                contentDescription = null
            )
        }
    }
}