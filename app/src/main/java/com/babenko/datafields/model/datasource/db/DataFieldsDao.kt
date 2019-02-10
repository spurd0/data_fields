package com.babenko.datafields.model.datasource.db

import android.arch.persistence.room.*
import com.babenko.datafields.model.entity.DataField
import io.reactivex.Single

@Dao
interface DataFieldsDao {
    @Query("SELECT * FROM data_field")
    fun getDataFields(): Single<List<DataField>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dataFields: List<DataField>)

    @Transaction
    fun insert(vararg dataFields: List<DataField>) {

    }

    @Query("DELETE FROM data_field")
    fun deleteAll()
}