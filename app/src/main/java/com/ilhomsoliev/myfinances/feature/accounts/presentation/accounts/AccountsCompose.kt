package com.ilhomsoliev.myfinances.feature.accounts.presentation.accounts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ilhomsoliev.myfinances.R
import com.ilhomsoliev.myfinances.shared.model.enumeration.NavIconState
import com.ilhomsoliev.myfinances.shared.model.ui.account.AccountUi
import com.ilhomsoliev.myfinances.shared.sharedUi.FFloatingActionButton
import com.ilhomsoliev.myfinances.shared.sharedUi.NavBar
import com.ilhomsoliev.myfinances.shared.sharedUi.RootTopAppBar

data class AccountState(
    val accounts: List<AccountUi>,
    val amount: Double,
)

interface AccountCallback {
    fun onNavBarSelect(point: Int)
    fun onMenuClick()
    fun onSearchClick()
    fun onSaveNewAccount(name: String, amount: String, iconId: Int)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountsContent(
    state: AccountState,
    callback: AccountCallback,
) {
    var isDialogActive by remember {
        mutableStateOf(false)
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        topBar = {
            RootTopAppBar(
                text = "Мои счета",
                onMenuClick = {
                    callback.onMenuClick()
                },
                onSearchClick = {
                    callback.onSearchClick()
                })
        },
        bottomBar = {
            NavBar(
                state = mutableListOf(
                    NavIconState.INACTIVE, NavIconState.ACTIVE, NavIconState.INACTIVE
                ),
                modifier = Modifier
            ) { callback.onNavBarSelect(it) }
        },
        floatingActionButton = {
            FFloatingActionButton {
                isDialogActive = true
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            LazyColumn(modifier = Modifier.fillMaxWidth(), content = {
                item {
                    Text(
                        text = state.amount.toString() + "$", // TODO change Symbol
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center),
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontSize = 28.sp,
                            lineHeight = 34.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF45669A),
                        ), textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                }
                item {

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center),
                        text = "All Balance",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontSize = 16.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF45669A),
                        ), textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(24.dp))

                }
                items(state.accounts) {
                    AccountItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 14.dp),
                        icon = R.drawable.ic_search,
                        name = it.name,
                        amount = it.amount,
                        currency = it.currency.symbol,
                    )
                    Spacer(modifier = Modifier.height(18.dp))
                }
            })
        }
    }
    AddAccountDialog(isDialogOpen = isDialogActive, onSaveClick = { name, amount, iconId ->
        callback.onSaveNewAccount(name, amount, iconId)
        isDialogActive = false
    }, onDismiss = {
        isDialogActive = false
    })
}

@Composable
private fun AccountItem(
    modifier: Modifier = Modifier,
    icon: Int,
    name: String,
    amount: Double,
    currency: Char
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(15.dp))
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp, horizontal = 22.dp)
        ) {
            Image(painter = painterResource(id = icon), contentDescription = null)
            Spacer(modifier = Modifier.width(24.dp))
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = name, style = MaterialTheme.typography.labelLarge.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFFC8C8C8),
                    )
                )
                Text(
                    text = "$amount $currency", style = MaterialTheme.typography.labelLarge.copy(
                        fontSize = 24.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF979797),
                    )
                )
            }
        }

    }

}