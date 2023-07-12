package com.mrtsv9.organizr.android

import android.app.Application
import com.mrtsv9.organizr.di.commonModule
import org.koin.core.context.startKoin

class OrganizrApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(commonModule)
        }
    }
}