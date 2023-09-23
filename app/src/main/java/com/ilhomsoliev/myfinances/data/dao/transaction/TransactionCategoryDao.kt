package com.ilhomsoliev.myfinances.data.dao.transaction

import androidx.room.Dao
import androidx.room.Query
import com.ilhomsoliev.myfinances.data.dao.ext.BaseDao
import com.ilhomsoliev.myfinances.data.model.transaction.TransactionCategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionCategoryDao : BaseDao<TransactionCategoryEntity> {

    @Query("SELECT * FROM `transaction_category` WHERE type = :type")
    fun getCategories(type: String): Flow<List<TransactionCategoryEntity>>

    @Query("SELECT * FROM transaction_category WHERE id = :categoryId")
    suspend fun getCategoryById(categoryId: Int): TransactionCategoryEntity?

}