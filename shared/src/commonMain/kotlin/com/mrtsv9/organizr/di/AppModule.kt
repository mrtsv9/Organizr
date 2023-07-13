package com.mrtsv9.organizr.di

import com.mrtsv9.organizr.data.local.DatabaseDriverFactory
import com.mrtsv9.organizr.data.local.repository.TaskRepositoryImpl
import com.mrtsv9.organizr.data.local.task.TaskDaoImpl
import com.mrtsv9.organizr.database.TaskDatabase
import com.mrtsv9.organizr.domain.local.repository.TaskRepository
import com.mrtsv9.organizr.domain.local.task.TaskDao
import org.koin.core.module.Module
import org.koin.dsl.module

expect fun platformModule(): Module

val commonModule = module {
    platformModule()
//    single<TaskRepository> { TaskRepositoryImpl(get()) }
}