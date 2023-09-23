package com.ilhomsoliev.myfinances.data.dao.goal

import androidx.room.Dao
import androidx.room.Query
import com.ilhomsoliev.myfinances.data.dao.ext.BaseDao
import com.ilhomsoliev.myfinances.data.model.goals.GoalEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GoalDao : BaseDao<GoalEntity> {

    @Query("SELECT * FROM `goal` WHERE state = :state ORDER BY dateCreated DESC")
    fun getGoals(state: String): Flow<List<GoalEntity>>

    @Query("SELECT * FROM `goal` WHERE id = :goalId")
    suspend fun getGoalById(goalId: Int): GoalEntity?

    @Query("SELECT * FROM `goal` WHERE id = :goalId")
    fun getGoalByIdAsFlow(goalId: Int): Flow<GoalEntity?>

}