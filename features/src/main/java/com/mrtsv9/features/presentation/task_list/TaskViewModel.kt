package com.mrtsv9.features.presentation.task_list

import com.mrtsv9.features.presentation.task_list.model.TaskListAction
import com.mrtsv9.features.presentation.task_list.model.TaskListIntent
import com.mrtsv9.features.presentation.task_list.model.TaskListState
import com.mrtsv9.features.presentation.task_list.model.toTaskItem
import com.mrtsv9.organizr.domain.local.repository.TaskRepository
import com.mrtsv9.organizr.domain.local.task.Task
import kotlinx.coroutines.flow.onEach
import pro.respawn.flowmvi.android.MVIViewModel

class TaskViewModel(
    repository: TaskRepository
) : MVIViewModel<TaskListState, TaskListIntent, TaskListAction>(initialState = TaskListState.Loading) {

    init {
        repository.getAllTasks()
            .onEach(::produceState)
            .consume()
    }

    override suspend fun reduce(intent: TaskListIntent) {
        when (intent) {
            is TaskListIntent.AddTaskClicked -> {
                // TODO:
            }

            is TaskListIntent.FabClicked -> {
                // TODO:
            }
        }
    }

    private suspend fun produceState(tasks: List<Task>) = updateState {
        TaskListState.DisplayingTasks(
            tasks = tasks.map(Task::toTaskItem)
        )
    }

}