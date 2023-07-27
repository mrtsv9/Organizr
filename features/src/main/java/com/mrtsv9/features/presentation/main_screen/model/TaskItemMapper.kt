package com.mrtsv9.features.presentation.main_screen.model

import com.mrtsv9.organizr.domain.local.task.Task

fun Task.toTaskItem(): TaskItem = TaskItem(
    id = id,
    title = title,
    timeLength = timeLength,
    createdAt = createdAt
)

fun TaskItem.toTask(): Task = Task(
    id = id,
    title = title,
    timeLength = timeLength,
    createdAt = createdAt
)