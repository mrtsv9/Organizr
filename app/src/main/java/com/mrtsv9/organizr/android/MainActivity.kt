package com.mrtsv9.organizr.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mrtsv9.features.presentation.main_screen.MainScreen
import com.mrtsv9.features.presentation.main_screen.TaskScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OrganizrTheme {
                MainScreen()
            }
        }
    }
}