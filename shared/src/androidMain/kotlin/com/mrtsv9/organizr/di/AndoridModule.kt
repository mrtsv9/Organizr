package com.mrtsv9.organizr.di

import com.mrtsv9.organizr.data.local.DatabaseDriverFactory
import com.mrtsv9.organizr.data.local.task.TaskDaoImpl
import com.mrtsv9.organizr.database.TaskDatabase
import com.mrtsv9.organizr.domain.local.task.TaskDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual fun platformModule() = module {
    single<TaskDatabase> {
        TaskDatabase(driver = DatabaseDriverFactory(context = androidContext()).createDriver())
    }
    single<TaskDao> { TaskDaoImpl(get()) }
}