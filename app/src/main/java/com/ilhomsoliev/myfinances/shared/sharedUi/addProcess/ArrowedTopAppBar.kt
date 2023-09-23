package com.ilhomsoliev.myfinances.shared.sharedUi.addProcess

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.ilhomsoliev.myfinances.R

@Composable
fun ArrowedTopAppBar(
    modifier: Modifier = Modifier,
    onPrevClick: () -> Unit,
    onNextClick: () -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = {
            onPrevClick()
        }) {
            Image(painter = painterResource(id = R.drawable.ic_arrow_left), null)
        }
        IconButton(onClick = {
            onNextClick()
        }) {
            Image(painter = painterResource(id = R.drawable.ic_arrow_right), null)
        }
    }

}