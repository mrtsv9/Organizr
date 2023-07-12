package com.mrtsv9.organizr.data.local.task

import com.mrtsv9.organizr.domain.local.task.Task
import database.TaskEntity
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun TaskEntity.toTask(): Task {
    return Task(
        id = id,
        title = title,
        timeLength = timeLength,
        createdAt = Instant.fromEpochMilliseconds(createdAt)
            .toLocalDateTime(TimeZone.currentSystemDefault())
    )
}