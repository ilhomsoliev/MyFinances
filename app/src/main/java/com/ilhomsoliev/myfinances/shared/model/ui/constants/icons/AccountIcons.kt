package com.ilhomsoliev.myfinances.shared.model.ui.constants.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.ilhomsoliev.myfinances.R

data class AccountIconModel(
    val id: Int,
    val backgroundColor: Int, // color to int
    val icon: Int, // resourceId
)

fun getIcons(): List<AccountIconModel> = listOf(
    AccountIconModel(
        id = 1,
        backgroundColor = Color.White.toArgb(),
        icon = R.drawable.ic_heart,
    ),
)

fun getIconById(id: Int): AccountIconModel =
    if (id < getIcons().size)
        getIcons()[id]
    else getIconById(0)


