package com.mrtsv9.organizr.presentation.task_list

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.mrtsv9.organizr.R
import com.mrtsv9.organizr.domain.local.repository.TaskRepository
import com.mrtsv9.organizr.domain.local.task.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import pro.respawn.flowmvi.DelicateStoreApi
import pro.respawn.flowmvi.android.MVIViewModel
import kotlin.random.Random

class TaskViewModel(
    repository: TaskRepository
) : MVIViewModel<TaskListState, TaskListIntent, TaskListAction>(initialState = TaskListState.Loading) {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertTask(
                Task(
                    0, "random task", Random.nextLong(1, 8), Clock.System.now().toLocalDateTime(
                        TimeZone.currentSystemDefault()
                    )
                )
            )
            repository.getAllTasks().onEach(::produceState)
        }
    }

    @OptIn(DelicateStoreApi::class)
    override fun recover(from: Exception): TaskListState {
        send(TaskListAction.ShowSnackbar("Error"))
        return state
    }

    override suspend fun reduce(intent: TaskListIntent) {
        when (intent) {
            is TaskListIntent.AddTaskClicked -> {
                TaskListState.Loading
            }

            is TaskListIntent.FabClicked -> {
                TaskListState.Loading
            }
        }
    }

    private suspend fun produceState(tasks: List<Task>) = updateState {
        Log.d("TAG", "produceState: $tasks")
        TaskListState.DisplayingTasks(
            tasks = tasks.map(Task::toTaskItem)
        )
    }

}