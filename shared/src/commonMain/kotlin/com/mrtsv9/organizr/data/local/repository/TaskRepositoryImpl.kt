package com.mrtsv9.organizr.data.local.repository

import com.mrtsv9.organizr.domain.local.repository.TaskRepository
import com.mrtsv9.organizr.domain.local.task.Task
import com.mrtsv9.organizr.domain.local.task.TaskDao

class TaskRepositoryImpl(private val dao: TaskDao) : TaskRepository {

    override suspend fun getAllTasks(): List<Task> {
        return dao.getAllTasks()
    }

}