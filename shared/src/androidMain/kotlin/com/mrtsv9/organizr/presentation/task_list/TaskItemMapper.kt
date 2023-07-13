package com.mrtsv9.organizr.presentation.task_list

import com.mrtsv9.organizr.domain.local.task.Task

fun Task.toTaskItem(): TaskItem {
    return TaskItem(
        id = id,
        title = title,
        timeLength = timeLength,
        createdAt = createdAt
    )
}