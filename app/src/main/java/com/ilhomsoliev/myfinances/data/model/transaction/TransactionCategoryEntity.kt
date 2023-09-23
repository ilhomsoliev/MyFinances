package com.ilhomsoliev.myfinances.data.model.transaction

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction_category")
data class TransactionCategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val iconId: Int,
    val type: String,
    val dateCreated: Long,
)