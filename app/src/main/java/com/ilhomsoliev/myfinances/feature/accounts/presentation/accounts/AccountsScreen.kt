package com.ilhomsoliev.myfinances.feature.accounts.presentation.accounts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import com.ilhomsoliev.myfinances.app.navigation.Screens
import com.ilhomsoliev.myfinances.feature.accounts.viewmodel.AccountsViewModel
import com.ilhomsoliev.myfinances.shared.sharedUi.navBarNavigate
import kotlinx.coroutines.launch

@Composable
fun AccountsScreen(
    vm: AccountsViewModel,
    navController: NavController,
) {
    val scope = rememberCoroutineScope()
    val amount by vm.amount.collectAsState()
    val accounts by vm.accounts.collectAsState(initial = emptyList())

    LaunchedEffect(key1 = Unit, block = {
        vm.getAmount()
    })


    AccountsContent(
        state = AccountState(
            amount = amount ?: 0.0,
            accounts = accounts,
        ),
        callback = object : AccountCallback {
            override fun onNavBarSelect(point: Int) {
                if (point == 1) return
                navController.navigate(navBarNavigate(point))
            }

            override fun onMenuClick() {
                navController.navigate(Screens.MenuBottomSheet.route)
            }

            override fun onSearchClick() {

            }

            override fun onSaveNewAccount(name: String, amount: String, iconId: Int) {
                scope.launch { vm.createNewAccount(name, amount, iconId) }
            }
        })

}