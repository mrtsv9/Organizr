package com.mrtsv9.features.presentation.main_screen

import androidx.lifecycle.viewModelScope
import com.mrtsv9.features.presentation.main_screen.model.MainScreenAction
import com.mrtsv9.features.presentation.main_screen.model.MainScreenIntent
import com.mrtsv9.features.presentation.main_screen.model.MainScreenState
import com.mrtsv9.features.presentation.main_screen.model.toTask
import com.mrtsv9.features.presentation.main_screen.model.toTaskItem
import com.mrtsv9.organizr.domain.local.repository.MainRepository
import com.mrtsv9.organizr.domain.local.task.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import pro.respawn.flowmvi.android.MVIViewModel

class MainViewModel(
    private val repository: MainRepository
) : MVIViewModel<MainScreenState, MainScreenIntent, MainScreenAction>(initialState = MainScreenState.Loading) {

    init {
        repository.getAllTasks().onEach(::updateTasksState).consume()
    }

    override suspend fun reduce(intent: MainScreenIntent) {
        when (intent) {
            is MainScreenIntent.AddOrEditTaskClicked -> {
                send(MainScreenAction.ShowEditScreen(intent.taskItem))
            }

            is MainScreenIntent.FabClicked -> {

            }

            is MainScreenIntent.SaveTaskClicked -> {
                viewModelScope.launch(Dispatchers.IO) {
                    repository.insertTask(intent.taskItem.toTask())
                    repository.getAllTasks().collectLatest {
                        updateTasksState(it)
                    }
                }
            }
        }
    }

    private suspend fun updateTasksState(tasks: List<Task>) = updateState {
        MainScreenState.DisplayingTasks(
            tasks = tasks.map(Task::toTaskItem)
        )
    }

}