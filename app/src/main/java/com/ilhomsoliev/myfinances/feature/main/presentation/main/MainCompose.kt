package com.ilhomsoliev.myfinances.feature.main.presentation.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ilhomsoliev.myfinances.R
import com.ilhomsoliev.myfinances.shared.model.enumeration.NavIconState
import com.ilhomsoliev.myfinances.shared.model.enumeration.TransactionType
import com.ilhomsoliev.myfinances.shared.model.ui.transaction.TransactionUi
import com.ilhomsoliev.myfinances.shared.sharedUi.FFloatingActionButton
import com.ilhomsoliev.myfinances.shared.sharedUi.NavBar

data class MainState(
    val activeTransactionType: TransactionType,
    val isDialogTransactionTypeActive: Boolean,
    val transactions: List<TransactionUi>
)

interface MainCallback {
    fun onNavBarSelect(point: Int)
    fun onMenuClick()
    fun onSearchClick()
    fun changeTransactionTypeDialogState(value: Boolean)
    fun onTypeTransactionClick(type: TransactionType)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(
    state: MainState,
    callback: MainCallback,
) {

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        topBar = {
            TopAppBar(activeType = state.activeTransactionType, onMenuClick = {
                callback.onMenuClick()
            }, onSearchClick = {
                callback.onSearchClick()
            })
        },
        bottomBar = {
            NavBar(
                state = mutableListOf(
                    NavIconState.ACTIVE, NavIconState.INACTIVE, NavIconState.INACTIVE
                ),
                modifier = Modifier
            ) { callback.onNavBarSelect(it) }
        }, floatingActionButton = {
            //if (state.goals.isNotEmpty())
            FFloatingActionButton {
                callback.changeTransactionTypeDialogState(true)
            }
        }
    ) { padding ->
        Box(Modifier.padding(padding)) {

        }
    }
    AddTransactionDialog(isActive = state.isDialogTransactionTypeActive, onTypeClick = {
        callback.onTypeTransactionClick(it)
        callback.changeTransactionTypeDialogState(false)
    }) {
        callback.changeTransactionTypeDialogState(false)
    }
}

@Composable
private fun TopAppBar(
    activeType: TransactionType,
    onMenuClick: () -> Unit,
    onSearchClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 21.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = { onMenuClick() }) {
            Image(
                painter = painterResource(id = R.drawable.ic_menu_hamburger),
                contentDescription = null
            )
        }

        Column {
            Text(text = "Income")
        }
        Column {
            Text(text = "Outcome")
        }

        IconButton(onClick = { onSearchClick() }) {
            Image(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = null
            )
        }

    }
}