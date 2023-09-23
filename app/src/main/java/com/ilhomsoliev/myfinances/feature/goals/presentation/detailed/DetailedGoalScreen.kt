package com.ilhomsoliev.myfinances.feature.goals.presentation.detailed

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import com.ilhomsoliev.myfinances.app.navigation.Screens
import com.ilhomsoliev.myfinances.feature.goals.viewmodel.DetailedGoalViewModel
import kotlinx.coroutines.launch

@Composable
fun DetailedGoalScreen(
    vm: DetailedGoalViewModel,
    navController: NavController,
    goalId: Int,
) {

    val scope = rememberCoroutineScope()

    val goal by vm.goal.collectAsState()
    val refills by vm.refills.collectAsState(initial = emptyList())

    LaunchedEffect(key1 = Unit, block = {
        vm.loadGoal(goalId)
    })

    DetailedGoalContent(
        state = DetailedGoalState(goal = goal, refiils = refills),
        callback = object : DetailedGoalCallback {
            override fun onBack() {
                navController.popBackStack()
            }

            override fun onSettings() {
                navController.navigate(
                    Screens.DetailedGoalMenuBottomSheet.route.replace(
                        "{goalId}",
                        goal?.id.toString()
                    )
                )
            }

            override fun onSaveClick(sum: String) {
                scope.launch { vm.insertRefill(sum) }
            }
        })
}