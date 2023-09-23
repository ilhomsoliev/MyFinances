package com.ilhomsoliev.myfinances.feature.onboarding.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.ilhomsoliev.myfinances.R
import com.ilhomsoliev.myfinances.shared.sharedUi.CustomButton

data class OnBoardingState(
    val currentTab: Int,
)

interface OnBoardingCallback {
    fun next()
    fun onSkipClick()
    fun onTabChange(tab: Int)
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingContent(
    state: OnBoardingState,
    callback: OnBoardingCallback
) {

    val pagerState: PagerState = rememberPagerState(initialPage = state.currentTab)

    LaunchedEffect(pagerState.currentPage) {
        callback.onTabChange(pagerState.currentPage)
    }

    LaunchedEffect(key1 = state.currentTab, block = {
        pagerState.animateScrollToPage(state.currentTab)
    })

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        HorizontalPager(
            state = pagerState,
            count = onBoardingData.size,
            itemSpacing = 12.dp,
        ) { selectTab: Int ->
            Box(modifier = Modifier.fillMaxSize()) {
                OnBoardingPage(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 140.dp),
                    image = onBoardingData[selectTab].first,
                    text = onBoardingData[selectTab].second,
                )
                /*      if (selectTab == 0)
                          SentResponds(
                              modifier = Modifier.padding(vertical = 8.dp),
                              responds = state.sentResponds,
                              localResponds = state.localSentResponds,
                              callback = callback
                          )
                      else
                          ReceivedResponds(
                              modifier = Modifier.padding(vertical = 8.dp),
                              responds = state.receivedResponds,
                              respondsStates = state.respondsStates,
                              callback = callback
                          )*/
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 54.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                repeat(3) {
                    Box(
                        modifier = Modifier
                            .padding(start = if (it != 0) 14.dp else 0.dp)
                            .size(15.dp)
                            .clip(CircleShape)
                            .background(if (it < state.currentTab) Color.White else Color(0xFFC0C0C0))

                    )
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                modifier = Modifier
                    .padding(bottom = 14.dp)
                    .clickable {
                        callback.onSkipClick()
                    }, text = "Пропустить", style = MaterialTheme.typography.labelMedium
            )
            CustomButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                text = if (state.currentTab == 2) "Вперед!" else "Продолжить"
            ) {
                callback.next()
            }
        }
    }
}

@Composable
private fun OnBoardingPage(
    modifier: Modifier = Modifier,
    image: Int,
    text: String,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(243.dp, 210.dp),
            painter = painterResource(id = image),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(56.dp))
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = text, style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 20.sp,
                lineHeight = 22.sp,
                color = Color(0xFF0B2D74),
                textAlign = TextAlign.Center,
            )
        )
    }

}

val onBoardingData = listOf(
    Pair(
        R.drawable.board_1,
        "Начните контролировать свои доходы и расходы вместе со Smart moneyНачните контролировать свои доходы и расходы вместе со Smart money"
    ),
    Pair(
        R.drawable.board_2,
        "Smart money поможет вам закрыть ваши кредитные карты в кратчайшие сроки"
    ),
    Pair(
        R.drawable.board_3,
        "Со Smart money у вас наконец получится накопить на любую вашу мечту"
    ),
)