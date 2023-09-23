package com.ilhomsoliev.myfinances.feature.main.presentation.main

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.NavController
import com.ilhomsoliev.myfinances.app.navigation.Screens
import com.ilhomsoliev.myfinances.feature.main.viewmodel.MainViewModel
import com.ilhomsoliev.myfinances.shared.model.enumeration.TransactionType
import com.ilhomsoliev.myfinances.shared.sharedUi.navBarNavigate
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    vm: MainViewModel,
    navController: NavController,
) {
    val scope = rememberCoroutineScope()
    val isDialogTransactionTypeActive by vm.isDialogTransactionTypeActive.collectAsState()
    LaunchedEffect(key1 = Unit, block = {
        Log.d("Hello Colors ", Color(0xFFFFF282).toArgb().toString())
    })
    MainContent(
        state = MainState(
            activeTransactionType = TransactionType.Income,
            isDialogTransactionTypeActive = isDialogTransactionTypeActive,
            transactions = emptyList(),
        ), callback = object : MainCallback {
            override fun onNavBarSelect(point: Int) {
                if (point == 0) return
                navController.navigate(navBarNavigate(point))
            }

            override fun onMenuClick() {
                navController.navigate(Screens.MenuBottomSheet.route)
            }

            override fun onSearchClick() {

            }

            override fun changeTransactionTypeDialogState(value: Boolean) {
                scope.launch { vm.changeTransactionTypeDialogState(value) }
            }

            override fun onTypeTransactionClick(type: TransactionType) {
                navController.navigate(
                    Screens.AddTransactionScreen.route.replace(
                        "{transactionType}",
                        type.name
                    )
                )
            }
        })

}