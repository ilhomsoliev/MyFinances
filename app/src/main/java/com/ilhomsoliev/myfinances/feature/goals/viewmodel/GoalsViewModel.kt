package com.ilhomsoliev.myfinances.feature.goals.viewmodel

import androidx.lifecycle.ViewModel
import com.ilhomsoliev.myfinances.data.repository.GoalRepository
import com.ilhomsoliev.myfinances.shared.model.enumeration.goal.GoalState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest

class GoalsViewModel(
    private val repository: GoalRepository,
) : ViewModel() {

    private val _activeGoalState = MutableStateFlow(GoalState.Active)
    val activeGoalState = _activeGoalState.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val goals by lazy {
        combine(_activeGoalState) {
            _activeGoalState.value
        }.flatMapLatest {
            repository.getGoals(_activeGoalState.value)
        }
    }

    suspend fun changeActiveGoalType(value: GoalState) {
        _activeGoalState.emit(value)
    }
}