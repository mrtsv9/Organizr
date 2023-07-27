package com.mrtsv9.organizr.domain.local.repository

import com.mrtsv9.organizr.domain.local.task.Task
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    fun getAllTasks(): Flow<List<Task>>
    suspend fun insertTask(task: Task): Task

}