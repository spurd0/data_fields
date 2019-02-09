package com.babenko.datafields.application

import android.app.Application
import com.babenko.datafields.BuildConfig
import com.babenko.datafields.application.dagger.*
import com.babenko.datafields.application.dagger.modules.ContextModule
import com.babenko.datafields.application.dagger.modules.RetrofitModule
import com.squareup.leakcanary.LeakCanary
import timber.log.Timber

class DataFieldsApplication : Application() {
    companion object {
        lateinit var viewComponent: ViewComponent private set
        lateinit var presenterComponent: PresenterComponent private set
        lateinit var modelComponent: ModelComponent private set
        lateinit var repositoryComponent: RepositoryComponent private set
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
        viewComponent = initViewUtilityComponent()
        presenterComponent = initPresenterUtilityComponent()
        modelComponent = initModelComponent()
        repositoryComponent = initRepositoryComponent()
    }

    private fun initViewUtilityComponent(): ViewComponent {
        return DaggerViewComponent.builder()
            .build()
    }

    private fun initPresenterUtilityComponent(): PresenterComponent {
        return DaggerPresenterComponent.builder()
            .build()
    }

    private fun initModelComponent(): ModelComponent {
        return DaggerModelComponent.builder()
            .contextModule(ContextModule(this))
            .build()
    }

    private fun initRepositoryComponent(): RepositoryComponent {
        return DaggerRepositoryComponent.builder()
            .retrofitModule(RetrofitModule())
            .build()
    }
}