package com.ilhomsoliev.myfinances.feature.goals.presentation.goals

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import com.ilhomsoliev.myfinances.app.navigation.Screens
import com.ilhomsoliev.myfinances.feature.goals.viewmodel.GoalsViewModel
import com.ilhomsoliev.myfinances.shared.model.enumeration.goal.GoalState
import com.ilhomsoliev.myfinances.shared.model.ui.goal.GoalUi
import com.ilhomsoliev.myfinances.shared.sharedUi.navBarNavigate
import kotlinx.coroutines.launch

@Composable
fun GoalsScreen(
    vm: GoalsViewModel,
    navController: NavController,
) {

    val scope = rememberCoroutineScope()
    val goals by vm.goals.collectAsState(initial = emptyList())
    val goalType by vm.activeGoalState.collectAsState()

    GoalsContent(
        state = GoalsState(
            goalState = goalType,
            goals = goals // + listOf(GoalUiDemo, GoalUiDemo)
        ),
        callback = object : GoalsCallback {
            override fun onNavBarSelect(point: Int) {
                if (point == 2) return
                navController.navigate(navBarNavigate(point))
            }

            override fun onMenuClick() {
                navController.navigate(Screens.MenuBottomSheet.route)
            }

            override fun onSearchClick() {

            }

            override fun onGoalTypeClick(value: GoalState) {
                scope.launch { vm.changeActiveGoalType(value) }
            }

            override fun addGoal() {
                navController.navigate(Screens.AddGoalScreen.route)
            }

            override fun onGoalClick(item: GoalUi) {
                navController.navigate(
                    Screens.DetailedGoalScreen.route.replace(
                        "{goalId}",
                        "${item.id}"
                    )
                )
            }
        })

}