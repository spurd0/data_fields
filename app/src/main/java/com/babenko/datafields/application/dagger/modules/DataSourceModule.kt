package com.babenko.datafields.application.dagger.modules

import android.arch.persistence.room.Room
import android.content.Context
import com.babenko.datafields.application.db.DataFieldsDatabase
import com.babenko.datafields.application.storage.Prefs
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataSourceModule {
    @Singleton
    @Provides
    fun providePrefs(context: Context): Prefs {
        return Prefs(context)
    }

    @Provides
    @Singleton
    internal fun provideDatabase(context: Context): DataFieldsDatabase {
        return Room.databaseBuilder(context, DataFieldsDatabase::class.java, "data_fields_database")
            .fallbackToDestructiveMigration()
            .build()
    }
}