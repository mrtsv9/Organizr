package com.mrtsv9.organizr.presentation

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import com.mrtsv9.organizr.presentation.task_list.TaskListAction
import com.mrtsv9.organizr.presentation.task_list.TaskListIntent
import com.mrtsv9.organizr.presentation.task_list.TaskListState
import com.mrtsv9.organizr.presentation.task_list.TaskViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.koinViewModel
import pro.respawn.flowmvi.android.compose.ConsumerScope
import pro.respawn.flowmvi.android.compose.MVIComposable

@Composable
@Suppress("ComposableFunctionName")
fun AppScreen() = MVIComposable(getViewModel<TaskViewModel>()) { state ->
//fun AppScreen() = MVIComposable(koinViewModel<TaskViewModel>()) { state ->

    val scaffoldState = rememberScaffoldState()

    consume { action ->
        when (action) {
            is TaskListAction.ShowSnackbar -> {
                Log.d("TAG", "AppScreen: ShowSnackbar")
            }
        }
        when (state) {
            is TaskListState.Loading -> {
                Log.d("TAG", "AppScreen: Loading")
            }

            is TaskListState.DisplayingTasks -> {
                Log.d("TAG", "AppScreen: ${state.tasks}")
            }
        }
    }

    Scaffold(Modifier.fillMaxSize(), scaffoldState = scaffoldState) {
        AppScreenContent(state, Modifier.padding(it))
    }

}

@Composable
private fun ConsumerScope<TaskListIntent, TaskListAction>.AppScreenContent(
    state: TaskListState,
    modifier: Modifier = Modifier
) {
    Text("Hello world")
}
