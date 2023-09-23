package com.ilhomsoliev.myfinances.feature.main.presentation.addTransaction.chooseCategory

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ilhomsoliev.myfinances.shared.model.ui.transaction.TransactionCategoryUi
import com.ilhomsoliev.myfinances.shared.sharedUi.addProcess.AddIcon
import com.ilhomsoliev.myfinances.shared.sharedUi.addProcess.CustomIcon

@Composable
fun ChooseTransactionCategoryScreen(
    categories: List<TransactionCategoryUi>,
    selectedCategoryId: Int?,
    onCategoryClick: (Int) -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Выберите категорию", style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF45669A),
            )
        )
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 26.dp),
            columns = GridCells.Fixed(4),
            verticalArrangement = Arrangement.spacedBy(26.dp),
            content = {
                items(categories) {
                    TransactionCategoryItem(categoryUi = it, onClick = {
                        onCategoryClick(it.id)
                    })
                }
                item {
                    AddIcon(modifier = Modifier.wrapContentSize()) {
                        // TODO
                    }
                }
            })
    }
}

@Composable
fun TransactionCategoryItem(
    categoryUi: TransactionCategoryUi,
    onClick: () -> Unit,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        CustomIcon(image = categoryUi.icon.image, colors = categoryUi.icon.getColorsGraphic()) {
            onClick()
        }
        Text(
            text = categoryUi.name, style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 10.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF45669A),
            )
        )
    }
}