package com.ilhomsoliev.myfinances.feature.goals.presentation.bottom.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ilhomsoliev.myfinances.R
import com.ilhomsoliev.myfinances.app.theme.colors.Gradients
import com.ilhomsoliev.myfinances.feature.bottomSheet.menu.MenuItem

data class DetailedGoalMenuBsState(
    val isReminderActive: Boolean,
)

interface DetailedGoalMenuBsCallback {
    fun onReminderClick()
    fun onChangeGoal()
    fun onFinishGoal()
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun DetailedGoalMenuBsContent(
    state: DetailedGoalMenuBsState,
    callback: DetailedGoalMenuBsCallback,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp))
            .padding(vertical = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    callback.onReminderClick()
                }
                .padding(horizontal = 28.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_settings_gear),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(25.dp))
                Text(
                    text = "Напоминать мне", style = MaterialTheme.typography.labelLarge.copy(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(500),
                        brush = Brush.horizontalGradient(Gradients.blue())
                    )
                )
            }
            Switch(checked = state.isReminderActive, onCheckedChange = {
                callback.onReminderClick()
            })
            //Image(painter = painterResource(id = R.drawable.ic_arrow_right), contentDescription = null)
        }

        MenuItem(
            icon = R.drawable.ic_discount,
            text = "Изменить цель",
            onClick = { callback.onChangeGoal() })

        MenuItem(
            icon = R.drawable.ic_star,
            text = "Завершить",
            onClick = { callback.onFinishGoal() })

        Spacer(modifier = Modifier.height(20.dp))

    }

}