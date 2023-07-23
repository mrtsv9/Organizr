package com.mrtsv9.features.presentation.task_list

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrtsv9.features.presentation.task_list.model.TaskListAction
import com.mrtsv9.features.presentation.task_list.model.TaskListIntent
import com.mrtsv9.features.presentation.task_list.model.TaskListState
import com.mrtsv9.organizr.domain.local.repository.TaskRepository
import com.mrtsv9.organizr.domain.local.task.TaskDao
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import org.koin.compose.getKoin
import pro.respawn.flowmvi.android.compose.ConsumerScope
import pro.respawn.flowmvi.android.compose.MVIComposable


class TestViewModel(private val repository: TaskRepository) : ViewModel() {
    init {
        viewModelScope.launch {
            repository.getAllTasks()
        }
    }
}

@Composable
fun TestScreen() {
    val viewModel = getViewModel<TestViewModel>()
    val repo = getKoin().get<TaskRepository>()
    Text(text = "Test View Model")
}

@Composable
@Suppress("ComposableFunctionName")
fun TaskScreen() = MVIComposable(getViewModel<TaskViewModel>()) { state ->

    Text("Hello world")

    val scaffoldState = rememberScaffoldState()

    consume { action ->
        when (action) {
            is TaskListAction.ShowSnackbar -> {
                Log.d("TAG", "AppScreen: ShowSnackbar")
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
    when (state) {
        is TaskListState.Loading -> {
            Log.d("TAG", "AppScreen: Loading")
        }

        is TaskListState.DisplayingTasks -> {
            Log.d("TAG", "AppScreen: ${state.tasks}")
        }
    }
    Text("Hello world")
}
