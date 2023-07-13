package com.mrtsv9.organizr.presentation.task_list

import pro.respawn.flowmvi.MVIAction
import pro.respawn.flowmvi.MVIIntent
import pro.respawn.flowmvi.MVIState

sealed interface TaskListState : MVIState {
    object Loading : TaskListState
    data class DisplayingTasks(
        val tasks: List<TaskItem>
    ) : TaskListState
}

sealed interface TaskListIntent : MVIIntent {
    object FabClicked : TaskListIntent
    object AddTaskClicked : TaskListIntent
}

sealed interface TaskListAction : MVIAction {
    data class ShowSnackbar(val message: String) : TaskListAction
}