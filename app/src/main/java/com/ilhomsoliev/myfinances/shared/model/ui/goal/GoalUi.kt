package com.ilhomsoliev.myfinances.shared.model.ui.goal

import com.ilhomsoliev.myfinances.data.model.goals.GoalEntity
import com.ilhomsoliev.myfinances.shared.model.enumeration.goal.GoalState
import com.ilhomsoliev.myfinances.shared.model.ui.constants.CurrencyModel
import com.ilhomsoliev.myfinances.shared.model.ui.constants.getCurrency

data class GoalUi(
    val id: Int? = null,
    val name: String,
    val targetAmount: Double,
    val currentAmount: Double,
    val imageUrl: String?,
    val dateCreated: Long,
    val isReminderActive: Boolean,
    val comment: String = "", // Disabled
    val currency: CurrencyModel,
    val deadline: Long,
    val state: GoalState,
)

fun GoalEntity.map(currentAmount: Double) = GoalUi(
    id = id,
    name = name,
    targetAmount = targetAmount,
    currentAmount = currentAmount,
    comment = comment,
    imageUrl = imageUrl,
    dateCreated = dateCreated,
    isReminderActive = isReminderActive,
    currency = getCurrency(currencyId),
    deadline = deadline,
    state = GoalState.valueOf(state)
)

fun GoalUi.map() = GoalEntity(
    id = id,
    name = name,
    targetAmount = targetAmount,
    comment = comment,
    imageUrl = imageUrl,
    dateCreated = dateCreated,
    isReminderActive = isReminderActive,
    currencyId = currency.id,
    deadline = deadline,
    state = state.name
)

val GoalUiDemo = GoalUi(
    id = 1,
    name = "Buy a caar",
    targetAmount = 1234.0,
    currentAmount = 243.0,
    comment = "Buy my dream car lambargini",
    imageUrl = null,
    dateCreated = 13452345234,
    isReminderActive = true,
    currency = getCurrency(1),
    deadline = 123214123,
    state = GoalState.Active
)