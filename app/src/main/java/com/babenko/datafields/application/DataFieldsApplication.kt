package com.babenko.datafields.application

import android.app.Application
import com.babenko.datafields.BuildConfig
import com.babenko.datafields.application.dagger.AppComponent
import com.babenko.datafields.application.dagger.DaggerAppComponent
import com.babenko.datafields.application.dagger.modules.ContextModule
import com.babenko.datafields.application.dagger.modules.RetrofitModule
import com.squareup.leakcanary.LeakCanary
import timber.log.Timber

class DataFieldsApplication : Application() {
    companion object {
        lateinit var appComponent: AppComponent private set
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            initLeakCanary()
        }
        initComponents()
    }

    private fun initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
    }

    private fun initComponents() {
        appComponent = initPresenterUtilityComponent()
    }

    private fun initPresenterUtilityComponent(): AppComponent {
        return DaggerAppComponent.builder()
            .contextModule(ContextModule(this))
            .retrofitModule(RetrofitModule())
            .build()
    }
}