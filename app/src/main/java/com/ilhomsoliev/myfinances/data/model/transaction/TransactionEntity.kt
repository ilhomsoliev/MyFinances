package com.ilhomsoliev.myfinances.data.model.transaction

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val categoryId: Int, // CategoryEntity
    val amount: Double,
    val type: String, // TransactionType
    val comment: String,
    val accountId: Int, // AccountEntity
    val dateCreated: Long,
)