package com.ilhomsoliev.myfinances.feature.main.presentation.addTransaction

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ilhomsoliev.myfinances.feature.main.presentation.addTransaction.amount.TransactionAmountScreen
import com.ilhomsoliev.myfinances.feature.main.presentation.addTransaction.chooseCategory.ChooseTransactionCategoryScreen
import com.ilhomsoliev.myfinances.feature.main.viewmodel.AddTransactionViewModel
import com.ilhomsoliev.myfinances.shared.model.enumeration.TransactionType
import com.ilhomsoliev.myfinances.shared.sharedUi.addProcess.AddProcessButtons
import com.ilhomsoliev.myfinances.shared.sharedUi.addProcess.ArrowedTopAppBar
import com.ilhomsoliev.myfinances.shared.sharedUi.dialog.CustomAlertDialog
import kotlinx.coroutines.launch

const val NUMBER_SCREENS_ADD_TRANSACTION = 2

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun AddTransactionScreen(
    vm: AddTransactionViewModel,
    navController: NavController,
    transactionType: TransactionType
) {
    val scope = rememberCoroutineScope()
    val screen by vm.screen.collectAsState()
    val amount by vm.amount.collectAsState()
    val selectedTransactionCategory by vm.selectedTransactionCategoryId.collectAsState()
    val transactionCategories by vm.transactionCategories.collectAsState()
    var isAlertDialogActive by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = transactionType, block = {
        vm.getTransactionCategories(transactionType)
    })

    BackHandler {
        isAlertDialogActive = !isAlertDialogActive
    }

    val isButtonActive = when (screen) {
        0 -> selectedTransactionCategory != null
        1 -> amount.isNotEmpty()
        else -> true
    }

    Scaffold(modifier = Modifier.background(MaterialTheme.colorScheme.background), topBar = {
        ArrowedTopAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            onPrevClick = {
                if (screen == 0) isAlertDialogActive = true // TODO Add Dialog
                else scope.launch { vm.navigateBackward() }
            },
            onNextClick = {
                if (isButtonActive)
                    scope.launch {
                        vm.navigateForward(onSuccess = {
                            navController.popBackStack()
                        })
                    }
            }
        )
    }, bottomBar = {
        AddProcessButtons(
            modifier = Modifier.imePadding(),
            progress = (screen + 1).toDouble() / (NUMBER_SCREENS_ADD_TRANSACTION + 1).toDouble(),
            isActive = isButtonActive,
            onClick = {
                scope.launch {
                    vm.navigateForward(onSuccess = {
                        navController.popBackStack()
                    })
                }
            })
    }) {
        AnimatedContent(modifier = Modifier.padding(it), targetState = screen) {
            when (it) {
                0 -> ChooseTransactionCategoryScreen(
                    categories = transactionCategories,
                    selectedCategoryId = selectedTransactionCategory,
                    onCategoryClick = {
                        scope.launch { vm.selectTransactionCategory(it) }
                    },
                )

                else -> TransactionAmountScreen(
                    amount = amount,
                    onAmountChange = {
                        scope.launch { vm.changeAmount(it) }
                    })
            }
        }
    }
    CustomAlertDialog(
        isActive = isAlertDialogActive,
        title = "Вы действительно хотите выйти?",
        description = "Данные не сохранятся",
        positiveButtonLabel = "Да",
        negativeButtonLabel = "Нет",
        onPositiveButtonClick = {
            navController.popBackStack()
            isAlertDialogActive = false
        },
        onNegativeButtonClick = {
            isAlertDialogActive = false
        },
        onDismiss = {
            isAlertDialogActive = false
        },
    )
}