package com.ilhomsoliev.myfinances.feature.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilhomsoliev.myfinances.data.DataStoreManager
import com.ilhomsoliev.myfinances.data.repository.TransactionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class MainViewModel(
    private val transactionRepository: TransactionRepository,
    private val dataStoreManager: DataStoreManager,
) : ViewModel() {
    suspend fun changeTransactionTypeDialogState(value: Boolean) {
        _isDialogTransactionTypeActive.emit(value)
    }

    private val _hasLoggedIn = MutableStateFlow(false)
    val hasLoggedIn = _hasLoggedIn.asStateFlow()

    private val _isDialogTransactionTypeActive = MutableStateFlow(false)
    val isDialogTransactionTypeActive = _isDialogTransactionTypeActive.asStateFlow()

    val viewState = _hasLoggedIn.map { hasLoggedIn ->
        if (dataStoreManager.getIsFirstTime()) {
            ViewState.NotLoggedIn
        } else ViewState.LoggedIn
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = ViewState.Loading
    )


}

sealed class ViewState {
    object Loading : ViewState() // hasLoggedIn = unknown
    object LoggedIn : ViewState() // hasLoggedIn = true
    object NotLoggedIn : ViewState() // hasLoggedIn = false
}