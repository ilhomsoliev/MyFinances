package com.ilhomsoliev.myfinances.feature.goals.viewmodel.bottom

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilhomsoliev.myfinances.data.repository.GoalRepository
import com.ilhomsoliev.myfinances.shared.model.ui.goal.GoalUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class DetailedGoalMenuBsViewModel(
    private val repository: GoalRepository,
) : ViewModel() {

    private val _goal = MutableStateFlow<GoalUi?>(null)
    val goal = _goal.asStateFlow()

    suspend fun loadGoal(goalId: Int) {
        repository.getGoalByIdAsFlow(goalId).onEach {
            Log.d("Hello", it.toString())
            _goal.emit(it)
        }.launchIn(viewModelScope)
        Log.d("Hello id", goalId.toString())

//        _goal.emit(repository.getGoalById(goalId))
    }

    suspend fun onReminderClick() {
        _goal.value?.copy(
            isReminderActive = !((_goal.value?.isReminderActive) ?: false)
        )?.let {
            repository.insertGoal(
                it
            )
           // _goal.value?.id?.let { it1 -> loadGoal(it1) }
        }
    }
}