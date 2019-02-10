package com.babenko.datafields.model.datasource.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.babenko.datafields.model.entity.DataField

@Database(
    entities = [DataField::class],
    version = 1, exportSchema = false
)
abstract class DataFieldsDatabase : RoomDatabase() {
    abstract fun dataFieldsDao(): DataFieldsDao
}