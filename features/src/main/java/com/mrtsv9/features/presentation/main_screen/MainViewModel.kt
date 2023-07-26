package com.mrtsv9.features.presentation.main_screen

import androidx.lifecycle.viewModelScope
import com.mrtsv9.features.presentation.main_screen.model.MainScreenAction
import com.mrtsv9.features.presentation.main_screen.model.MainScreenIntent
import com.mrtsv9.features.presentation.main_screen.model.MainScreenState
import com.mrtsv9.features.presentation.main_screen.model.toTaskItem
import com.mrtsv9.organizr.domain.local.repository.MainRepository
import com.mrtsv9.organizr.domain.local.task.Task
import com.mrtsv9.organizr.domain.util.DateTimeUtil
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import pro.respawn.flowmvi.android.MVIViewModel
import java.util.UUID
import kotlin.random.Random

class MainViewModel(
    repository: MainRepository
) : MVIViewModel<MainScreenState, MainScreenIntent, MainScreenAction>(initialState = MainScreenState.Loading) {

    init {
        viewModelScope.launch {
            repeat(10) {
                repository.insertTask(
                    Task(
                        0,
                        UUID.randomUUID().toString(),
                        Random.nextLong(100),
                        DateTimeUtil.now()
                    )
                )
            }
        }
        repository.getAllTasks()
            .onEach(::produceState)
            .consume()
    }

    override suspend fun reduce(intent: MainScreenIntent) {
        when (intent) {
            is MainScreenIntent.EditTaskClicked -> {
                send(MainScreenAction.ShowEditScreen(null))
            }

            is MainScreenIntent.FabClicked -> {
                // TODO:
            }
        }
    }

    private suspend fun produceState(tasks: List<Task>) = updateState {
        MainScreenState.DisplayingTasks(
            tasks = tasks.map(Task::toTaskItem)
        )
    }

}