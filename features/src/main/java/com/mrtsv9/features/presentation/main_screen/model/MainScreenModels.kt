package com.mrtsv9.features.presentation.main_screen.model

import androidx.compose.runtime.Stable
import pro.respawn.flowmvi.MVIAction
import pro.respawn.flowmvi.MVIIntent
import pro.respawn.flowmvi.MVIState

@Stable
sealed interface MainScreenState : MVIState {
    object Loading : MainScreenState
    data class DisplayingTasks(
        var tasks: List<TaskItem>
    ) : MainScreenState
}

@Stable
sealed interface MainScreenIntent : MVIIntent {
    object FabClicked : MainScreenIntent
    data class AddOrEditTaskClicked(val taskItem: TaskItem?) : MainScreenIntent
    data class SaveTaskClicked(val taskItem: TaskItem) : MainScreenIntent
}

@Stable
sealed interface MainScreenAction : MVIAction {
    data class ShowSnackbar(val message: String) : MainScreenAction
    data class ShowEditScreen(val taskItem: TaskItem?) : MainScreenAction
}