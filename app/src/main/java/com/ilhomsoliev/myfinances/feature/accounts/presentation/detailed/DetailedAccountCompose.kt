package com.ilhomsoliev.myfinances.feature.accounts.presentation.detailed

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import com.ilhomsoliev.myfinances.shared.model.ui.account.AccountUi
import com.ilhomsoliev.myfinances.shared.model.ui.transaction.TransactionUi

data class DetailedAccountState(
    val account: AccountUi?,
    val transactions:List<TransactionUi>
)

interface DetailedAccountCallback {

}

@Composable
fun DetailedAccountContent(
    state: DetailedAccountState,
    callback: DetailedAccountCallback
) {

}