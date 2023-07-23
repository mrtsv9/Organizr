package com.mrtsv9.features.presentation.task_list.model

import com.mrtsv9.organizr.domain.local.task.Task

fun Task.toTaskItem(): TaskItem {
    return TaskItem(
        id = id,
        title = title,
        timeLength = timeLength,
        createdAt = createdAt
    )
}