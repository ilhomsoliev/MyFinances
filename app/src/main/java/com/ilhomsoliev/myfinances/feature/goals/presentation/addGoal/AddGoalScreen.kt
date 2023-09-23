package com.ilhomsoliev.myfinances.feature.goals.presentation.addGoal

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ilhomsoliev.myfinances.feature.goals.presentation.addGoal.amount.GoalAmountScreen
import com.ilhomsoliev.myfinances.feature.goals.presentation.addGoal.chooseCurrency.GoalChooseCurrencyScreen
import com.ilhomsoliev.myfinances.feature.goals.presentation.addGoal.name.GoalNameScreen
import com.ilhomsoliev.myfinances.feature.goals.presentation.addGoal.photo.GoalPhotoScreen
import com.ilhomsoliev.myfinances.feature.goals.viewmodel.AddGoalViewModel
import com.ilhomsoliev.myfinances.shared.sharedUi.addProcess.AddProcessButtons
import com.ilhomsoliev.myfinances.shared.sharedUi.addProcess.ArrowedTopAppBar
import com.ilhomsoliev.myfinances.shared.sharedUi.Loader
import kotlinx.coroutines.launch

const val NUMBER_SCREENS_ADD_GOAL = 4

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun AddGoalScreen(
    vm: AddGoalViewModel,
    navController: NavController,
) {
    val scope = rememberCoroutineScope()

    val screen by vm.screen.collectAsState()
    val selectedCurrency by vm.selectedCurrency.collectAsState()
    val targetAmount by vm.targetAmount.collectAsState()
    val goalName by vm.goalName.collectAsState()
    val isReminderActive by vm.isReminderActive.collectAsState()
    val imageUri by vm.imageUri.collectAsState()
    val imageUrl by vm.imageUrl.collectAsState()
    val isLoading by vm.isLoading.collectAsState()

    val isButtonActive = when (screen) {
        0 -> selectedCurrency != null
        1 -> targetAmount.isNotEmpty()
        2 -> goalName.isNotEmpty()
        else -> true
    }

    Scaffold(modifier = Modifier.background(MaterialTheme.colorScheme.background), topBar = {
        ArrowedTopAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            onPrevClick = {
                if (screen == 0) navController.popBackStack() // TODO Add Dialog
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
            isSkipAvailable = screen == NUMBER_SCREENS_ADD_GOAL - 1,
            isActive = isButtonActive,
            progress = (screen + 1).toDouble() / (NUMBER_SCREENS_ADD_GOAL + 1).toDouble(),
            onClick = {
                scope.launch {
                    vm.navigateForward(onSuccess = {
                        navController.popBackStack()
                    })
                }
            },
            onSkipClick = {
                scope.launch {
                    vm.navigateForward(onSuccess = {
                        navController.popBackStack()
                    })
                }
            })
    }) {
        AnimatedContent(modifier = Modifier.padding(it), targetState = screen) {
            when (it) {
                0 -> GoalChooseCurrencyScreen(
                    activeCurrencyModel = selectedCurrency,
                    onCurrencyClick = {
                        scope.launch { vm.changeCurrency(it) }
                    })

                1 -> GoalAmountScreen(
                    targetAmount = targetAmount,
                    symbol = selectedCurrency?.symbol ?: '$',
                    onAmountChange = {
                        scope.launch { vm.changeTargetAmount(it) }
                    })

                2 -> GoalNameScreen(
                    goalName = goalName,
                    isReminderActive = isReminderActive,
                    onGoalNameChange = {
                        scope.launch { vm.changeGoalName(it) }
                    },
                    onReminderClick = {
                        scope.launch { vm.changeReminderActive() }
                    })

                else -> GoalPhotoScreen(
                    photoUrl = imageUrl,
                    uri = imageUri,
                    onUriChange = {
                        scope.launch { vm.changeUri(it) }
                    }
                )
            }
        }
    }
    AnimatedVisibility(visible = isLoading) {
        Loader()
    }
}
