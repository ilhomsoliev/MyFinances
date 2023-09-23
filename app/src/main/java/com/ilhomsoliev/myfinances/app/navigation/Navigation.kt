package com.ilhomsoliev.myfinances.app.navigation

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ilhomsoliev.myfinances.data.DataStoreManager
import com.ilhomsoliev.myfinances.feature.accounts.AccountGraph
import com.ilhomsoliev.myfinances.feature.bottomSheet.menu.MenuBs
import com.ilhomsoliev.myfinances.feature.bottomSheet.menu.MenuBsCallback
import com.ilhomsoliev.myfinances.feature.goals.GoalGraph
import com.ilhomsoliev.myfinances.feature.main.MainGraph
import com.ilhomsoliev.myfinances.feature.onboarding.presentation.OnBoardingScreen
import com.ilhomsoliev.myfinances.feature.onboarding.viewmodel.OnBoardingViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.mp.KoinPlatformTools

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Navigation() {

    val context = LocalContext.current
    val navController = rememberNavController()

    val dataStore by lazy { KoinPlatformTools.defaultContext().get().get<DataStoreManager>() }
    ModalBottomSheetLayout(
        sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden),
        /*sheetShape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp)*/
        sheetContent = {
            MenuBs(object : MenuBsCallback {
                override fun openSettings() {

                }

                override fun openPurchase() {

                }

                override fun openAboutUs() {
                }

                override fun openRateUs() {
                }

            })
        },
    content = {
        NavHost(
            navController = navController,
            startDestination = Graph.MainGraph.route //Graph.GoalsGraph.route/*Screens.GoalsScreen.route*/
        ) {

            composable(route = Screens.OnBoardingScreen.route) {
                val viewModel = koinViewModel<OnBoardingViewModel>()
                OnBoardingScreen(
                    vm = viewModel,
                    navController = navController,
                    dataStoreManager = dataStore
                )
            }

            MainGraph(navController = navController, context = context)

            AccountGraph(navController = navController, context = context)

            GoalGraph(navController = navController, context = context)

            /*bottomSheet(route = Screens.MenuBottomSheet.route) {
                MenuBs(object : MenuBsCallback {
                    override fun openSettings() {

                    }

                    override fun openPurchase() {

                    }

                    override fun openAboutUs() {
                    }

                    override fun openRateUs() {
                    }

                })
            }*/

        }
    })
}
