package com.mrtsv9.organizr.domain.local.task

interface TaskDao {
    suspend fun insertTask(task: Task)
    suspend fun updateTask(task: Task)
    suspend fun getAllTasks(): List<Task>
    suspend fun getTaskById(id: Long): Task
    suspend fun deleteTaskById(id: Long)
}