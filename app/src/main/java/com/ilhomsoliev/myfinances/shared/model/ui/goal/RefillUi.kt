package com.ilhomsoliev.myfinances.shared.model.ui.goal

import com.ilhomsoliev.myfinances.data.model.goals.RefillEntity
import com.ilhomsoliev.myfinances.shared.model.enumeration.goal.RefillType
import com.ilhomsoliev.myfinances.shared.model.ui.constants.CurrencyModel
import com.ilhomsoliev.myfinances.shared.model.ui.constants.getCurrency

data class RefillUi(
    val id: Int? = null,
    val deadline: Long, // TODO Change to dateCreated
    val currency: CurrencyModel,
    val amount: Double,
    val type: RefillType,
    val goalId: Int,
)

fun RefillEntity.map() = RefillUi(
    id = id,
    deadline = deadline,
    currency = getCurrency(currencyId),
    amount = amount,
    type = RefillType.valueOf(type),
    goalId = goalId,
)

fun RefillUi.map() = RefillEntity(
    id = id,
    deadline = deadline,
    currencyId = currency.id,
    amount = amount,
    type = type.name,
    goalId = goalId,

    )

val RefillUiDemo = RefillUi(
    id = 1,
    deadline = 13453,
    currency = getCurrency(1),
    amount = 2134.0,
    type = RefillType.Add,
    goalId = 1,
)