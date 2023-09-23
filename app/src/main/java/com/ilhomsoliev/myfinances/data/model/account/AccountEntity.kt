package com.ilhomsoliev.myfinances.data.model.account

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "account")
data class AccountEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?=null,
    val name: String,
    val dateCreated: Long,
    val currencyId: Int,
    val iconId: Int,
)