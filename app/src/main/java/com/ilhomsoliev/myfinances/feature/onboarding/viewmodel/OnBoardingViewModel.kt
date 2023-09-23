package com.ilhomsoliev.myfinances.feature.onboarding.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilhomsoliev.myfinances.data.repository.TransactionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class OnBoardingViewModel(
    private val transactionRepository: TransactionRepository,
) : ViewModel() {
    private val _tabs =
        MutableStateFlow(0)
    val tabs =
        _tabs.asStateFlow()

    suspend fun selectTab(tab: Int) {
        _tabs.value = tab
    }
    init {
        //transactionRepository.
        viewModelScope
    }
}