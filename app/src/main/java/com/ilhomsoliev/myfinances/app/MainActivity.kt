package com.ilhomsoliev.myfinances.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ilhomsoliev.myfinances.app.navigation.Navigation
import com.ilhomsoliev.myfinances.app.theme.base.MyFinancesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyFinancesTheme {
                Navigation()
            }
        }
    }
}