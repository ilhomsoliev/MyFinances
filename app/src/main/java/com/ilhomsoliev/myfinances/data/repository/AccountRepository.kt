package com.ilhomsoliev.myfinances.data.repository

import android.util.Log
import com.ilhomsoliev.myfinances.data.dao.account.AccountDao
import com.ilhomsoliev.myfinances.data.dao.transaction.TransactionDao
import com.ilhomsoliev.myfinances.shared.model.ui.account.AccountUi
import com.ilhomsoliev.myfinances.shared.model.ui.account.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AccountRepository(
    private val accountDao: AccountDao,
    private val transactionDao: TransactionDao
) {
    suspend fun insertAccount(account: AccountUi) {
        accountDao.insert(account.map())
    }

    suspend fun getAccounts(): Flow<List<AccountUi>> =
        accountDao.getAccounts().map {
            Log.d("Hello", "HERe ${it.toString()}")
            it.map {
                it.map(it.id?.let { it1 ->
                    transactionDao.getAmountByAccountId(it1)
                } ?: 0.0)
            }
        }

    suspend fun getAmountOfAllAccounts() = transactionDao.getAmountOfAllAccounts()

}