package com.mrtsv9.organizr.domain.local.repository

import com.mrtsv9.organizr.domain.local.task.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    suspend fun getAllTasks(): Flow<List<Task>>
    suspend fun insertTask(task: Task)

}