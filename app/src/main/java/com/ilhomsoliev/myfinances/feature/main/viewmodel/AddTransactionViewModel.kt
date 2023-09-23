package com.ilhomsoliev.myfinances.feature.main.viewmodel

import androidx.lifecycle.ViewModel
import com.ilhomsoliev.myfinances.data.repository.TransactionRepository
import com.ilhomsoliev.myfinances.feature.main.presentation.addTransaction.NUMBER_SCREENS_ADD_TRANSACTION
import com.ilhomsoliev.myfinances.shared.model.enumeration.TransactionType
import com.ilhomsoliev.myfinances.shared.model.ui.transaction.TransactionCategoryUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AddTransactionViewModel(
    private val repository: TransactionRepository,
) : ViewModel() {

    private val _screen = MutableStateFlow(0)
    val screen = _screen.asStateFlow()

    private val _amount = MutableStateFlow("")
    val amount = _amount.asStateFlow()

    private val _goalName = MutableStateFlow("")
    val goalName = _goalName.asStateFlow()

    private val _transactionCategories = MutableStateFlow(emptyList<TransactionCategoryUi>())
    val transactionCategories = _transactionCategories.asStateFlow()


    private val _selectedTransactionCategoryId = MutableStateFlow<Int?>(null)
    val selectedTransactionCategoryId = _selectedTransactionCategoryId.asStateFlow()


    suspend fun navigateForward(onSuccess: () -> Unit) {
        if (_screen.value + 1 < NUMBER_SCREENS_ADD_TRANSACTION) {
            _screen.emit(_screen.value + 1)
        } else {
            //
        }
    }

    suspend fun navigateBackward() {
        if (_screen.value - 1 >= 0)
            _screen.emit(_screen.value - 1)
    }

    suspend fun changeAmount(targetAmount: String) {
        _amount.emit(targetAmount)
    }

    suspend fun changeGoalName(goalName: String) {
        _goalName.emit(goalName)
    }

    suspend fun getTransactionCategories(transactionType: TransactionType) {
        repository.getTransactionCategories(transactionType.name).collect {
            _transactionCategories.emit(it)
        }
    }

    suspend fun selectTransactionCategory(it: Int) {
        _selectedTransactionCategoryId.emit(it)
    }

}