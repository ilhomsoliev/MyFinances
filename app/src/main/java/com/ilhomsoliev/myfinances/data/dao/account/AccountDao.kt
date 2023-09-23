package com.ilhomsoliev.myfinances.data.dao.account

import androidx.room.Dao
import androidx.room.Query
import com.ilhomsoliev.myfinances.data.dao.ext.BaseDao
import com.ilhomsoliev.myfinances.data.model.account.AccountEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao : BaseDao<AccountEntity> {
    @Query("SELECT * FROM `account`")
    fun getAccounts(): Flow<List<AccountEntity>>

    @Query("SELECT * FROM `account` WHERE id = :accountId")
    suspend fun getAccountById(accountId: Int): AccountEntity?

}