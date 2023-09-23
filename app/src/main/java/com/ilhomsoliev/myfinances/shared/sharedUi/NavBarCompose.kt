package com.ilhomsoliev.myfinances.shared.sharedUi

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.SpaceEvenly
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ilhomsoliev.myfinances.R
import com.ilhomsoliev.myfinances.app.navigation.Graph
import com.ilhomsoliev.myfinances.shared.model.enumeration.NavIconState
import com.ilhomsoliev.myfinances.shared.model.enumeration.NavIconState.*


fun navBarNavigate(
    point: Int,
) = when (point) {
    0 -> Graph.MainGraph.route
    1 -> Graph.AccountGraph.route
    2 -> Graph.GoalsGraph.route
    else -> Graph.GoalsGraph.route
}

@Composable
fun NavBar(
    state: List<NavIconState>,
    modifier: Modifier = Modifier,
    onClick: (point: Int) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
            .background(colorScheme.primaryContainer),
        horizontalArrangement = SpaceEvenly, verticalAlignment = CenterVertically
    ) {
        state.forEachIndexed { i, state ->
            Item(
                icon = when (i) {
                    0 -> if (isSystemInDarkTheme())
                        if (state == ACTIVE)
                            R.drawable.ic_home_active
                        else R.drawable.ic_home_inactive
                    else
                        if (state == ACTIVE)
                            R.drawable.ic_home_active
                        else R.drawable.ic_home_inactive

                    1 -> if (isSystemInDarkTheme()) when (state) {
                        ACTIVE -> R.drawable.ic_account_active
                        INACTIVE -> R.drawable.ic_account_inactive
                    } else when (state) {
                        ACTIVE -> R.drawable.ic_account_active
                        INACTIVE -> R.drawable.ic_account_inactive
                    }

                    2 -> if (isSystemInDarkTheme()) when (state) {
                        ACTIVE -> R.drawable.ic_goal_active
                        INACTIVE -> R.drawable.ic_goal_inactive
                    } else when (state) {
                        ACTIVE -> R.drawable.ic_goal_active
                        INACTIVE -> R.drawable.ic_goal_inactive
                    }

                    else -> R.drawable.ic_home_active
                },
                state = state,
                onClick = { onClick(i) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Item(
    icon: Int,
    state: NavIconState,
    onClick: () -> Unit,
) {
    Card(
        onClick = onClick,
        shape = CircleShape,
        colors = cardColors(colorScheme.primaryContainer)
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = null,
            modifier = Modifier
                .padding(20.dp, 4.dp)
                .size(24.dp),
        )
    }
}