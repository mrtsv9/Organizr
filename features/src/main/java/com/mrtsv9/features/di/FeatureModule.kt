package com.mrtsv9.features.di

import com.mrtsv9.features.presentation.task_list.TaskViewModel
import com.mrtsv9.features.presentation.task_list.TestViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureModule = module {
    viewModel { TaskViewModel(get()) }
}