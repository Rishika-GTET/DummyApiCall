package com.example.dummyapicall.di
import android.app.Application
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class])
interface ApplicationComponent {
    fun inject(app: Application)
}
