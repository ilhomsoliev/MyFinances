package com.ilhomsoliev.myfinances.feature.main.presentation.addTransaction.amount

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ilhomsoliev.myfinances.shared.sharedUi.SumTextField

@Composable
fun TransactionAmountScreen(
    amount: String,
    onAmountChange: (String) -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "", style = MaterialTheme.typography.labelMedium.copy(
            fontSize = 30.sp,
            lineHeight = 35.sp,
            fontWeight = FontWeight(600),
            color = Color(0xFF45669A),
        ))
        /*SumTextField(

        )*/
    }

}