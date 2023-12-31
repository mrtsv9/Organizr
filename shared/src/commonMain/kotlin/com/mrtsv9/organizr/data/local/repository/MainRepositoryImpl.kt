package com.mrtsv9.organizr.data.local.repository

import com.mrtsv9.organizr.domain.local.repository.MainRepository
import com.mrtsv9.organizr.domain.local.task.Task
import com.mrtsv9.organizr.domain.local.task.TaskDao
import kotlinx.coroutines.flow.flow

class MainRepositoryImpl(private val dao: TaskDao) : MainRepository {

    override fun getAllTasks() = flow {
        emit(dao.getAllTasks())
    }

    override suspend fun insertTask(task: Task) {
        dao.insertTask(task)
    }
}