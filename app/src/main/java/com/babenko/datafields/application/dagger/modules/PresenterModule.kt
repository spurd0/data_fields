package com.babenko.datafields.application.dagger.modules

import com.babenko.datafields.model.interactor.LaunchInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresenterModule {
    @Singleton
    @Provides
    fun provideLaunchInteractor(): LaunchInteractor {
        return LaunchInteractor()
    }
}