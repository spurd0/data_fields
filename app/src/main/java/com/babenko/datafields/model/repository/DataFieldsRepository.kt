package com.babenko.datafields.model.repository

import com.babenko.datafields.application.db.DataFieldsDatabase
import com.babenko.datafields.application.rest.NetworkApi
import com.babenko.datafields.model.entity.DataField
import com.babenko.datafields.model.throwable.NoDataFieldsException
import io.reactivex.Single
import javax.inject.Inject

class DataFieldsRepository @Inject constructor(
    private val networkApi: NetworkApi,
    private val db: DataFieldsDatabase
) {
    fun requestDataFields(url: String): Single<List<DataField>> {
        return networkApi.requestDataFields(url)
    }

    fun saveDataFields(dataFields: List<DataField>) {
        db.dataFieldsDao().insert(dataFields)
    }

    fun getDataFields(): Single<List<DataField>> {
        return db.dataFieldsDao().getDataFields()
            .map {
                if (it.isEmpty()) throw NoDataFieldsException()
                it
            }
    }
}