package com.ilhomsoliev.myfinances.data.repository

import android.util.Log
import com.ilhomsoliev.myfinances.data.dao.goal.GoalDao
import com.ilhomsoliev.myfinances.data.dao.goal.RefillDao
import com.ilhomsoliev.myfinances.shared.model.enumeration.goal.GoalState
import com.ilhomsoliev.myfinances.shared.model.ui.goal.GoalUi
import com.ilhomsoliev.myfinances.shared.model.ui.goal.RefillUi
import com.ilhomsoliev.myfinances.shared.model.ui.goal.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GoalRepository(
    private val goalDao: GoalDao,
    private val refillDao: RefillDao,
) {
    suspend fun getGoals(state: GoalState): Flow<List<GoalUi>> =
        goalDao.getGoals(state.name).map {
            it.map {
                it.map(it.id?.let { it1 ->
                    refillDao.getCurrentAmountByGoalId(it1)
                } ?: 0.0)
            }
        }

    suspend fun insertGoal(goal: GoalUi) {
        goalDao.insert(goal.map())
    }

    suspend fun deleteGoal(goal: GoalUi) {
        goalDao.delete(goal.map())
    }

    suspend fun getGoalById(id: Int): GoalUi? {
        Log.d("Hello amount goal", refillDao.getCurrentAmountByGoalId(id).toString())
        return goalDao.getGoalById(id)?.map(refillDao.getCurrentAmountByGoalId(id) ?: 0.0)
    }

    suspend fun getGoalByIdAsFlow(id: Int): Flow<GoalUi?> {
        return goalDao.getGoalByIdAsFlow(id).map {
            it?.id?.let { it1 ->
                val currentAmount = refillDao.getCurrentAmountByGoalId(it1) ?: 0.0
                Log.d("Hello Cur", currentAmount.toString())
                it.map(currentAmount)
            }
        }
    }

    suspend fun getRefills(goalId: Int): Flow<List<RefillUi>> =
        refillDao.getRefills(goalId).map {
            it.map { it.map() }
        }

    suspend fun insertRefill(refill: RefillUi) {
        refillDao.insert(refill.map())
    }

    suspend fun deleteRefill(refill: RefillUi) {
        refillDao.delete(refill.map())
    }

    suspend fun getRefillById(id: Int): RefillUi? {
        return refillDao.getRefillById(id)?.map()
    }
}