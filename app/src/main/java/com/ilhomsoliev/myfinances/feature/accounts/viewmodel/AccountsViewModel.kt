package com.ilhomsoliev.myfinances.feature.accounts.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.ilhomsoliev.myfinances.data.repository.AccountRepository
import com.ilhomsoliev.myfinances.shared.model.ui.account.AccountUi
import com.ilhomsoliev.myfinances.shared.model.ui.constants.getCurrency
import com.ilhomsoliev.myfinances.shared.model.ui.constants.icons.getIconById
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow

class AccountsViewModel(
    private val accountRepository: AccountRepository,
) : ViewModel() {

    private val _amount = MutableStateFlow<Double?>(null)
    val amount = _amount.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val accounts by lazy {
        combine(_amount) {
            _amount.value != null // TODO wtf?
        }.flatMapLatest {
            if (it && _amount.value != null) accountRepository.getAccounts()
            else flow { listOf<AccountUi>() }
        }
    }

    suspend fun getAmount() {
        _amount.emit(accountRepository.getAmountOfAllAccounts() ?: 0.0)
    }

    suspend fun createNewAccount(name: String, amount: String, iconId: Int) {
        try {
            accountRepository.insertAccount(
                AccountUi(
                    id = null,
                    name = name,
                    dateCreated = System.currentTimeMillis(),
                    currency = getCurrency(1), // TODO
                    icon = getIconById(iconId),
                    amount = amount.toDouble(),
                )
            )
        } catch (e: Exception) {
            Log.d("Hello Exception", e.message.toString())
            // TODO show snackbar
        }

    }
}