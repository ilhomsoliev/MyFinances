package com.ilhomsoliev.myfinances.feature.goals.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilhomsoliev.myfinances.data.repository.GoalRepository
import com.ilhomsoliev.myfinances.shared.model.enumeration.goal.RefillType
import com.ilhomsoliev.myfinances.shared.model.ui.goal.GoalUi
import com.ilhomsoliev.myfinances.shared.model.ui.goal.RefillUi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class DetailedGoalViewModel(
    private val repository: GoalRepository
) : ViewModel() {

    private val _goal = MutableStateFlow<GoalUi?>(null)
    val goal = _goal.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val refills by lazy {
        combine(_goal) { // TODO
            _goal.value != null
        }.flatMapLatest {
            if (it && _goal.value != null) repository.getRefills(_goal.value!!.id!!)
            else flow { listOf<RefillUi>() }
        }
    }

    init {
        viewModelScope.launch {
            repository.getGoalById(1)
        }
    }

    suspend fun loadGoal(goalId: Int) {
        _goal.emit(repository.getGoalById(goalId))
    }

    suspend fun insertRefill(sum: String) {
        if (sum.isEmpty()) return
        _goal.value?.let { goal ->
            goal.id?.let {
                RefillUi(
                    id = null,
                    deadline = System.currentTimeMillis(),
                    currency = goal.currency,
                    amount = sum.toDouble(),
                    type = RefillType.Add, // TODO dynamic
                    goalId = it,
                )
            }?.let {
                repository.insertRefill(
                    it
                )
                _goal.value?.id?.let { it1 -> loadGoal(it1) }
            }
        }
    }
}