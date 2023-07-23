package com.mrtsv9.organizr.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mrtsv9.features.presentation.task_list.TaskScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OrganizrTheme {
                TaskScreen()
            }
        }
    }
}