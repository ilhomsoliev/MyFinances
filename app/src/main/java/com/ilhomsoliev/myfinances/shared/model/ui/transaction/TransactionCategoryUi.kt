package com.ilhomsoliev.myfinances.shared.model.ui.transaction

import com.ilhomsoliev.myfinances.data.model.transaction.TransactionCategoryEntity
import com.ilhomsoliev.myfinances.shared.model.ui.IconUi
import com.ilhomsoliev.myfinances.shared.model.ui.getIconById

data class TransactionCategoryUi(
    val id: Int,
    val name: String,
    val icon: IconUi,
    val type: String,
    val dateCreated: Long,
)

fun TransactionCategoryEntity.map() = TransactionCategoryUi(
    id = id,
    name = name,
    icon = getIconById(iconId),
    type = type,
    dateCreated = dateCreated,
)