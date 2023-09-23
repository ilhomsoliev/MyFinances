package com.ilhomsoliev.myfinances.shared.sharedUi

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ilhomsoliev.myfinances.R


@Composable
fun RootTopAppBar(
    text: String,
    onMenuClick: () -> Unit,
    onSearchClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = { onMenuClick() }) {
            Image(
                painter = painterResource(id = R.drawable.ic_menu_hamburger),
                contentDescription = null
            )
        }

        Text(
            text = text, style = MaterialTheme.typography.labelLarge.copy(
                fontSize = 18.sp,
                lineHeight = 22.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF0B2D74)
            )
        )

        IconButton(onClick = { onSearchClick() }) {
            Image(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = null
            )
        }
    }
}

