package com.ilhomsoliev.myfinances.feature.accounts.viewmodel

import androidx.lifecycle.ViewModel
import com.ilhomsoliev.myfinances.data.repository.AccountRepository

class DetailedAccountViewModel(
    private val accountRepository: AccountRepository,
) : ViewModel() {
}