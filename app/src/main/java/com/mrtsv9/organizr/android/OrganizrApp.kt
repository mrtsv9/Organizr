package com.mrtsv9.organizr.android

import android.app.Application
import com.mrtsv9.features.di.featureModule
import com.mrtsv9.organizr.di.commonModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class OrganizrApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@OrganizrApp)
            androidLogger()
//            val test = module {
//                viewModel { MainActivity.TestViewModel() }
//            }
//            modules(appModule)
            modules(listOf(commonModule(), featureModule))
        }
    }
}