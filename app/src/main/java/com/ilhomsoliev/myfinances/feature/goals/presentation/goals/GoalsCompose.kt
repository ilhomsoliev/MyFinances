package com.ilhomsoliev.myfinances.feature.goals.presentation.goals

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ilhomsoliev.myfinances.shared.model.enumeration.NavIconState
import com.ilhomsoliev.myfinances.shared.model.enumeration.goal.GoalState
import com.ilhomsoliev.myfinances.shared.model.ui.goal.GoalUi
import com.ilhomsoliev.myfinances.shared.sharedUi.CustomButton
import com.ilhomsoliev.myfinances.shared.sharedUi.FFloatingActionButton
import com.ilhomsoliev.myfinances.shared.sharedUi.NavBar
import com.ilhomsoliev.myfinances.shared.sharedUi.RootTopAppBar

data class GoalsState(
    val goalState: GoalState,
    val goals: List<GoalUi>,

    )

interface GoalsCallback {
    fun onNavBarSelect(point: Int)
    fun onMenuClick()
    fun onSearchClick()
    fun onGoalTypeClick(value: GoalState)
    fun addGoal()
    fun onGoalClick(item: GoalUi)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoalsContent(
    state: GoalsState,
    callback: GoalsCallback,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        topBar = {
            RootTopAppBar(text = "Мои цели", onMenuClick = {
                callback.onMenuClick()
            }, onSearchClick = {
                callback.onSearchClick()
            })
        },
        bottomBar = {
            NavBar(
                state = mutableListOf(
                    NavIconState.INACTIVE, NavIconState.INACTIVE, NavIconState.ACTIVE
                ),
                modifier = Modifier
            ) { callback.onNavBarSelect(it) }
        }, floatingActionButton = {
            if (state.goals.isNotEmpty())
                FFloatingActionButton {
                    callback.addGoal()
                }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)
            ) {
                TypeItem(
                    modifier = Modifier.weight(1f),
                    text = "Активные",
                    isActive = state.goalState == GoalState.Active
                ) {
                    callback.onGoalTypeClick(GoalState.Active)
                }
                Spacer(modifier = Modifier.width(8.dp))
                TypeItem(
                    modifier = Modifier.weight(1f),
                    text = "Завершенные",
                    isActive = state.goalState == GoalState.Finished
                ) {
                    callback.onGoalTypeClick(GoalState.Finished)
                }
            }
            if (state.goals.isEmpty())
                EmptyGoals(onAddClick = {
                    callback.addGoal()
                })
            else
                LazyColumn {
                    item {
                        Spacer(modifier = Modifier.height(28.dp))
                    }
                    items(state.goals) {
                        GoalItem(modifier = Modifier.fillMaxWidth(), goal = it, onClick = {
                            callback.onGoalClick(it)
                        })
                        Spacer(modifier = Modifier.padding(8.dp))
                    }
                }
        }
    }
}

@Composable
private fun EmptyGoals(onAddClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(bottom = 30.dp),
                text = "Целей еще нет",
                style = MaterialTheme.typography.labelLarge.copy(
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onTertiary
                )
            )
            Text(
                text = "Добавьте цель и мы поможем рассчитать как накопить к нужной дате",
                style = MaterialTheme.typography.labelMedium.copy(
                    textAlign = TextAlign.Center,
                    color = Color(0xFFC0C0C0)
                )
            )
            Spacer(modifier = Modifier.height(80.dp))
        }
        CustomButton(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .align(Alignment.BottomCenter)
                .padding(bottom = 70.dp),
            text = "Добавить"
        ) {
            onAddClick()
        }
    }
}

@Composable
private fun TypeItem(
    modifier: Modifier = Modifier,
    text: String,
    isActive: Boolean,
    onClick: () -> Unit,
) {

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .clickable {
                onClick()
            }, contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(vertical = 10.dp),
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 12.sp,
                color = if (isActive) Color(0xFF0B2D74) else Color(0xFFC0C0C0),
                fontWeight = FontWeight(600)
            )
        )
    }
}
