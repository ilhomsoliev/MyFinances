package com.ilhomsoliev.myfinances.shared.model.ui.account

import com.ilhomsoliev.myfinances.data.model.account.AccountEntity
import com.ilhomsoliev.myfinances.shared.model.ui.constants.CurrencyModel
import com.ilhomsoliev.myfinances.shared.model.ui.constants.getCurrency
import com.ilhomsoliev.myfinances.shared.model.ui.constants.icons.AccountIconModel
import com.ilhomsoliev.myfinances.shared.model.ui.constants.icons.getIconById

data class AccountUi(
    val id: Int? = null,
    val name: String,
    val dateCreated: Long,
    val currency: CurrencyModel,
    val icon: AccountIconModel,
    val amount: Double,
)


fun AccountEntity.map(currentAmount: Double) = AccountUi(
    id = id,
    name = name,
    amount = currentAmount,
    dateCreated = dateCreated,
    currency = getCurrency(currencyId),
    icon = getIconById(iconId),
)


fun AccountUi.map() = AccountEntity(
    id = id,
    name = name,
    dateCreated = dateCreated,
    currencyId = currency.id,
    iconId = icon.id,
)

