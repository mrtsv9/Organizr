package com.mrtsv9.organizr.domain.local.repository

import com.mrtsv9.organizr.domain.local.task.Task

interface TaskRepository {

    suspend fun getAllTasks(): List<Task>

}