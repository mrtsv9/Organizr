package com.mrtsv9.features.di

import com.mrtsv9.features.presentation.main_screen.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureModule = module {
    viewModel { MainViewModel(get()) }
}