package com.ilhomsoliev.myfinances.data.dao.transaction

import androidx.room.Dao
import androidx.room.Query
import com.ilhomsoliev.myfinances.data.dao.ext.BaseDao
import com.ilhomsoliev.myfinances.data.model.transaction.TransactionEntity
import com.ilhomsoliev.myfinances.shared.model.enumeration.TransactionType
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao : BaseDao<TransactionEntity> {

    @Query("SELECT * FROM `transaction` WHERE type = :type")
    fun getTransactions(type: String = TransactionType.Outcome.name): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM `transaction` WHERE id = :transactionId")
    suspend fun getTransactionById(transactionId: Int): TransactionEntity


    @Query(
        """SELECT 
SUM(CASE 
    WHEN type = 'Income' AND accountId = :accountId THEN amount
    WHEN type = 'Outcome' AND accountId = :accountId THEN -amount
    ELSE 0
  END) AS total_sum
FROM `transaction` """
    )
    suspend fun getAmountByAccountId(
        accountId: Int?
    ): Double?


    @Query(
        """SELECT 
SUM(CASE 
    WHEN type = 'Income' THEN amount
    WHEN type = 'Outcome' THEN -amount
    ELSE 0
  END) AS total_sum
FROM `transaction` """
    )
    suspend fun getAmountOfAllAccounts(): Double?

}
