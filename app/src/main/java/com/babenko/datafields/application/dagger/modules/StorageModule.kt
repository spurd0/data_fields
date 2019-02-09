package com.babenko.datafields.application.dagger.modules

import android.content.Context
import com.babenko.datafields.model.datasource.storage.Prefs
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule {
    @Singleton
    @Provides
    fun providePrefs(context: Context): Prefs {
        return Prefs(context)
    }
}