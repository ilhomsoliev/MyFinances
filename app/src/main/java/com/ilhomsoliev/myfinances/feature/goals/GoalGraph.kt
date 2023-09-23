package com.ilhomsoliev.myfinances.feature.goals

import android.content.Context
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet
import com.ilhomsoliev.myfinances.app.navigation.Graph
import com.ilhomsoliev.myfinances.app.navigation.Screens
import com.ilhomsoliev.myfinances.feature.goals.presentation.addGoal.AddGoalScreen
import com.ilhomsoliev.myfinances.feature.goals.presentation.bottom.menu.DetailedGoalMenuBs
import com.ilhomsoliev.myfinances.feature.goals.presentation.detailed.DetailedGoalScreen
import com.ilhomsoliev.myfinances.feature.goals.presentation.goals.GoalsScreen
import com.ilhomsoliev.myfinances.feature.goals.viewmodel.AddGoalViewModel
import com.ilhomsoliev.myfinances.feature.goals.viewmodel.DetailedGoalViewModel
import com.ilhomsoliev.myfinances.feature.goals.viewmodel.GoalsViewModel
import com.ilhomsoliev.myfinances.feature.goals.viewmodel.bottom.DetailedGoalMenuBsViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterialNavigationApi::class)
fun NavGraphBuilder.GoalGraph(navController: NavHostController, context: Context) {
    navigation(
        route = Graph.GoalsGraph.route,
        startDestination = Screens.GoalsScreen.route
    ) {

        composable(route = Screens.GoalsScreen.route) {
            val viewModel = koinViewModel<GoalsViewModel>()
            GoalsScreen(vm = viewModel, navController = navController)
        }
        composable(route = Screens.AddGoalScreen.route) {
            val viewModel = koinViewModel<AddGoalViewModel>()
            AddGoalScreen(vm = viewModel, navController = navController)
        }
        composable(route = Screens.DetailedGoalScreen.route, arguments = listOf(
            navArgument("goalId") {
                type = NavType.IntType
                defaultValue = 0
            }
        )) { backStackEntry ->
            val goalId = backStackEntry.arguments?.getInt("goalId") ?: 0
            val viewModel = koinViewModel<DetailedGoalViewModel>()
            DetailedGoalScreen(vm = viewModel, navController = navController, goalId = goalId)
        }
        bottomSheet(route = Screens.DetailedGoalMenuBottomSheet.route, arguments = listOf(
            navArgument("goalId") {
                type = NavType.IntType
                defaultValue = 0
            }
        )) { backStackEntry ->
            val goalId = backStackEntry.arguments?.getInt("goalId") ?: 0
            val viewModel = koinViewModel<DetailedGoalMenuBsViewModel>()
            DetailedGoalMenuBs(viewModel,navController, goalId)
        }

    }
}