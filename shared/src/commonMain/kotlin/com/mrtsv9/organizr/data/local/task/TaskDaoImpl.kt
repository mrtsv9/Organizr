package com.mrtsv9.organizr.data.local.task

import com.mrtsv9.organizr.database.TaskDatabase
import com.mrtsv9.organizr.domain.local.task.Task
import com.mrtsv9.organizr.domain.local.task.TaskDao
import com.mrtsv9.organizr.domain.util.DateTimeUtil

class TaskDaoImpl(db: TaskDatabase) : TaskDao {

    private val queries = db.taskQueries

    override suspend fun insertTask(task: Task) {
        queries.insertTask(
            id = task.id,
            title = task.title,
            timeLength = task.timeLength,
            createdAt = DateTimeUtil.toEpochMillis(task.createdAt)
        )
    }

    override suspend fun getAllTasks(): List<Task> {
        return queries.getAllTasks().executeAsList().map { it.toTask() }
    }

    override suspend fun getTaskById(id: Long): Task {
        return queries.getTaskById(id).executeAsOne().toTask()
    }

    override suspend fun deleteTaskById(id: Long) {
        return queries.deleteTaskById(id)
    }
}