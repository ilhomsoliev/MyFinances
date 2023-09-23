package com.ilhomsoliev.myfinances.data.repository

import com.ilhomsoliev.myfinances.data.dao.transaction.TransactionCategoryDao
import com.ilhomsoliev.myfinances.data.dao.transaction.TransactionDao
import com.ilhomsoliev.myfinances.shared.model.ui.transaction.TransactionCategoryUi
import com.ilhomsoliev.myfinances.shared.model.ui.transaction.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TransactionRepository(
    private val transactionDao: TransactionDao,
    private val transactionCategoryDao: TransactionCategoryDao
) {

    suspend fun getTransactionCategories(type:String): Flow<List<TransactionCategoryUi>> =
        transactionCategoryDao.getCategories(type).map {
            it.map {
                it.map()
            }
        }



}