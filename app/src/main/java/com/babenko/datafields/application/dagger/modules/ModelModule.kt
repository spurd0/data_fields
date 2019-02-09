package com.babenko.datafields.application.dagger.modules

import com.babenko.datafields.model.repository.AppInfoRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ModelModule {
    @Singleton
    @Provides
    fun provideAppRepository(): AppInfoRepository {
        return AppInfoRepository()
    }
}