package com.ilhomsoliev.myfinances.feature.onboarding.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import com.ilhomsoliev.myfinances.app.navigation.Graph
import com.ilhomsoliev.myfinances.data.DataStoreManager
import com.ilhomsoliev.myfinances.feature.onboarding.viewmodel.OnBoardingViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun OnBoardingScreen(
    vm: OnBoardingViewModel,
    navController: NavController,
    dataStoreManager: DataStoreManager,
) {
    val scope = rememberCoroutineScope()

    val tab by vm.tabs.collectAsState()

    OnBoardingContent(state = OnBoardingState(tab), object : OnBoardingCallback {
        override fun next() {
            if (tab == 2) {
                this.onSkipClick()
                return
            }
            scope.launch { vm.selectTab(tab + 1) }
        }

        override fun onSkipClick() {
            scope.launch {
                dataStoreManager.changeIsFirstTime(false)
                this.launch(Dispatchers.Main) {
                    navController.popBackStack()
                    navController.navigate(Graph.MainGraph.route)
                }
            }
        }

        override fun onTabChange(tab: Int) {
            scope.launch { vm.selectTab(tab) }
        }
    })
}