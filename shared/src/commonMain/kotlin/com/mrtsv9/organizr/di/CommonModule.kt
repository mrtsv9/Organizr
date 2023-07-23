package com.mrtsv9.organizr.di

import com.mrtsv9.organizr.data.local.repository.TaskRepositoryImpl
import com.mrtsv9.organizr.data.local.task.TaskDaoImpl
import com.mrtsv9.organizr.domain.local.repository.TaskRepository
import com.mrtsv9.organizr.domain.local.task.TaskDao
import org.koin.core.annotation.KoinInternalApi
import org.koin.core.module.Module
import org.koin.dsl.module

expect fun platformModule(): Module

@OptIn(KoinInternalApi::class)
fun commonModule() = module {
    includedModules.add(platformModule())
    single<TaskDao> { TaskDaoImpl(get()) }
    factory<TaskRepository> { TaskRepositoryImpl(get()) }
}