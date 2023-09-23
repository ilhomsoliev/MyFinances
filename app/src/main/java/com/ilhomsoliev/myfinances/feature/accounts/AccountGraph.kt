package com.ilhomsoliev.myfinances.feature.accounts

import android.content.Context
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ilhomsoliev.myfinances.app.navigation.Graph
import com.ilhomsoliev.myfinances.app.navigation.Screens
import com.ilhomsoliev.myfinances.feature.accounts.presentation.accounts.AccountsScreen
import com.ilhomsoliev.myfinances.feature.accounts.presentation.detailed.DetailedAccountScreen
import com.ilhomsoliev.myfinances.feature.accounts.viewmodel.AccountsViewModel
import com.ilhomsoliev.myfinances.feature.accounts.viewmodel.DetailedAccountViewModel
import org.koin.androidx.compose.koinViewModel


fun NavGraphBuilder.AccountGraph(navController: NavHostController, context: Context) {
    navigation(
        route = Graph.AccountGraph.route,
        startDestination = Screens.AccountScreen.route
    ) {
        composable(route = Screens.AccountScreen.route) {
            val viewModel = koinViewModel<AccountsViewModel>()
            AccountsScreen(vm = viewModel, navController = navController)
        }
        composable(route = Screens.AccountScreen.route) {
            val viewModel = koinViewModel<DetailedAccountViewModel>()
            DetailedAccountScreen(vm = viewModel, navController = navController)
        }
    }
}
