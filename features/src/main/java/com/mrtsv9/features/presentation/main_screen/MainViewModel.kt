package com.mrtsv9.features.presentation.main_screen

import androidx.lifecycle.viewModelScope
import com.mrtsv9.features.presentation.main_screen.model.MainScreenAction
import com.mrtsv9.features.presentation.main_screen.model.MainScreenIntent
import com.mrtsv9.features.presentation.main_screen.model.MainScreenState
import com.mrtsv9.features.presentation.main_screen.model.TaskItem
import com.mrtsv9.features.presentation.main_screen.model.toTask
import com.mrtsv9.features.presentation.main_screen.model.toTaskItem
import com.mrtsv9.organizr.domain.local.repository.MainRepository
import com.mrtsv9.organizr.domain.local.task.Task
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import pro.respawn.flowmvi.android.MVIViewModel

class MainViewModel(
    private val repository: MainRepository
) : MVIViewModel<MainScreenState, MainScreenIntent, MainScreenAction>(initialState = MainScreenState.Loading) {

    init {
//        viewModelScope.launch {
//            repeat(10) {
//                repository.insertTask(
//                    Task(
//                        0,
//                        UUID.randomUUID().toString(),
//                        Random.nextLong(100),
//                        DateTimeUtil.now()
//                    )
//                )
//            }
//        }
        repository.getAllTasks()
            .onEach(::updateTasksState)
            .consume()
    }

    override suspend fun reduce(intent: MainScreenIntent) {
        when (intent) {
            is MainScreenIntent.AddOrEditTaskClicked -> {
                send(MainScreenAction.ShowEditScreen(intent.taskItem))
            }

            is MainScreenIntent.FabClicked -> {

            }

            is MainScreenIntent.SaveTaskClicked -> {
                viewModelScope.launch {
                    val insertedTask = repository.insertTask(intent.taskItem.toTask())
                    updateTasksState(listOf(insertedTask))
                }
            }
        }
    }

    private suspend fun updateTasksState(tasks: List<Task>) = updateState {
        val current = this as? MainScreenState.DisplayingTasks
        val allTasks = current?.tasks?.plus(tasks.map(Task::toTaskItem)) ?: tasks.map(Task::toTaskItem)
        MainScreenState.DisplayingTasks(
            tasks = allTasks
        )
    }

}