package com.ilhomsoliev.myfinances.feature.goals.presentation.goals

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ilhomsoliev.myfinances.R
import com.ilhomsoliev.myfinances.shared.model.ui.goal.GoalUi
import com.ilhomsoliev.myfinances.shared.sharedUi.CustomProgressBar
import com.ilhomsoliev.myfinances.shared.sharedUi.ImageBox

@Composable
fun GoalItem(
    modifier: Modifier = Modifier,
    goal: GoalUi,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .background(Color.White)
            .clickable {
                onClick()
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {
            ImageBox(
                modifier = Modifier
                    .padding(end = 18.dp)
                    .size(72.dp)
                    .clip(RoundedCornerShape(9.dp)), imageHttp = goal.imageUrl
            ) {
                GoalIcon(
                    modifier = Modifier
                        .padding(end = 18.dp)
                        .size(72.dp), // TODO change to dynamic
                    icon = R.drawable.ic_saving,
                    color = Color.Blue
                )
            }
            Column {
                Text(
                    modifier = Modifier.padding(bottom = 3.dp),
                    text = goal.name, style = MaterialTheme.typography.labelMedium.copy(
                        fontSize = 12.sp,
                        fontWeight = FontWeight(500),
                        color = MaterialTheme.colorScheme.onTertiary
                    )
                )
                Text(
                    modifier = Modifier.padding(bottom = 8.dp),
                    text = "${goal.currentAmount}${goal.currency.symbol}/ ${goal.targetAmount}${goal.currency.symbol}",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFF979797),
                    )
                )
                CustomProgressBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 9.dp),
                    progress = goal.currentAmount / goal.targetAmount
                )
                Text(
                    text = "${(goal.currentAmount / goal.targetAmount * 100).toInt()}% from goal",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontSize = 12.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF646464),
                    )
                )
            }
        }
    }
}

@Composable
private fun GoalIcon(
    modifier: Modifier = Modifier,
    icon: Int,
    color: Color,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(9.dp))
            .background(Color(0x141AA8E9C)),
        contentAlignment = Alignment.Center
    ) {
        Image(painter = painterResource(id = icon), contentDescription = null)
    }

}