package com.ilhomsoliev.myfinances.shared.model.ui.transaction

data class TransactionUi(
    val id: Int,
    val name: String,
    val categoryId: Int,// TODO // CategoryEntity
    val amount: Double,
    val type: String, // TransactionType
    val comment: String,
    val accountId: Int, // AccountEntity
    val dateCreated: Long,
)