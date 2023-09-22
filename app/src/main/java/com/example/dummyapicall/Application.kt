package com.example.dummyapicall

import android.app.Application
import com.example.dummyapicall.di.AppModule
import com.example.dummyapicall.di.ApplicationComponent
import com.example.dummyapicall.di.DaggerApplicationComponent


class MyApplication : Application() {
    companion object {
        lateinit var component: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerApplicationComponent.builder()
            .appModule(AppModule())
            .build()
        component.inject(this)
    }
}



