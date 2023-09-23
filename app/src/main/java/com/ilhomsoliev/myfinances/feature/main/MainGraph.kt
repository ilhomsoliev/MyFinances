package com.ilhomsoliev.myfinances.feature.main

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.ilhomsoliev.myfinances.app.navigation.Graph
import com.ilhomsoliev.myfinances.app.navigation.Screens
import com.ilhomsoliev.myfinances.feature.main.presentation.addTransaction.AddTransactionScreen
import com.ilhomsoliev.myfinances.feature.main.presentation.main.MainScreen
import com.ilhomsoliev.myfinances.feature.main.viewmodel.AddTransactionViewModel
import com.ilhomsoliev.myfinances.feature.main.viewmodel.MainViewModel
import com.ilhomsoliev.myfinances.feature.main.viewmodel.ViewState
import com.ilhomsoliev.myfinances.shared.model.enumeration.TransactionType
import org.koin.androidx.compose.koinViewModel

class MainGraph

fun NavGraphBuilder.MainGraph(navController: NavHostController, context: Context) {
    navigation(
        route = Graph.MainGraph.route,
        startDestination = Screens.MainScreen.route
    ) {
        composable(route = Screens.MainScreen.route) {
            val viewModel = koinViewModel<MainViewModel>()

            val viewState by viewModel.viewState.collectAsState()

            when (viewState) {
                ViewState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                ViewState.NotLoggedIn -> {
                    LaunchedEffect(Unit) {
                        navController.popBackStack()
                        navController.navigate(Screens.OnBoardingScreen.route)
                    }
                }

                ViewState.LoggedIn -> {
                    MainScreen(viewModel, navController)
                }
            }
        }
        composable(route = Screens.AddTransactionScreen.route, arguments = listOf(
            navArgument("transactionType") {
                type = NavType.StringType
                defaultValue = ""
            }
        )) { backStackEntry ->
            val transactionType = backStackEntry.arguments?.getString("transactionType") ?: ""
            val viewModel = koinViewModel<AddTransactionViewModel>()
            AddTransactionScreen(
                vm = viewModel,
                navController = navController,
                transactionType = TransactionType.valueOf(transactionType)
            )

        }
    }
}
