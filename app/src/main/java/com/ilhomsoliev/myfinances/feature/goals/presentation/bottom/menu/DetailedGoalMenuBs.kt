package com.ilhomsoliev.myfinances.feature.goals.presentation.bottom.menu

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import com.ilhomsoliev.myfinances.feature.goals.viewmodel.bottom.DetailedGoalMenuBsViewModel
import kotlinx.coroutines.launch


@Composable
fun DetailedGoalMenuBs(
    vm: DetailedGoalMenuBsViewModel, navController: NavController, goalId: Int,
) {
    val scope = rememberCoroutineScope()
    val goal by vm.goal.collectAsState()

    LaunchedEffect(key1 = Unit, block = {
        vm.loadGoal(goalId)
    })


    DetailedGoalMenuBsContent(
        state = DetailedGoalMenuBsState(goal?.isReminderActive ?: false),
        callback = object : DetailedGoalMenuBsCallback {
            override fun onReminderClick() {
                scope.launch { vm.onReminderClick() }
            }

            override fun onChangeGoal() {
                TODO("Not yet implemented")
            }

            override fun onFinishGoal() {

                TODO("Not yet implemented")
            }

        })
}