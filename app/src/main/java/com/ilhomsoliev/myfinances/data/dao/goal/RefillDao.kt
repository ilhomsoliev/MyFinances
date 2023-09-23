package com.ilhomsoliev.myfinances.data.dao.goal

import androidx.room.Dao
import androidx.room.Query
import com.ilhomsoliev.myfinances.data.dao.ext.BaseDao
import com.ilhomsoliev.myfinances.data.model.goals.RefillEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RefillDao : BaseDao<RefillEntity> {

    @Query("SELECT * FROM `refill` WHERE goalId = :goalId")
    fun getRefills(goalId: Int): Flow<List<RefillEntity>>

    @Query("SELECT * FROM `refill` WHERE id = :refillId")
    suspend fun getRefillById(refillId: Int): RefillEntity?

    @Query(
        """SELECT 
SUM(CASE 
    WHEN type = 'Add' AND goalId = :goalId THEN amount
    WHEN type = 'Minus' AND goalId = :goalId THEN -amount
    ELSE 0
  END) AS total_sum
FROM refill """
    )
    suspend fun getCurrentAmountByGoalId(
        goalId: Int?
    ): Double?


}