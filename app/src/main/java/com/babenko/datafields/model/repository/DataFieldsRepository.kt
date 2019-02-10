package com.babenko.datafields.model.repository

import com.babenko.datafields.model.datasource.rest.NetworkApi
import com.babenko.datafields.model.datasource.rest.config.ServerEndpoint
import com.babenko.datafields.model.entity.DataField
import com.babenko.datafields.model.throwable.NoDataFieldsException
import io.reactivex.Single
import io.reactivex.internal.operators.single.SingleFromCallable
import javax.inject.Inject

class DataFieldsRepository @Inject constructor(private val networkApi: NetworkApi) {
    fun requestDataFields(endpoint: ServerEndpoint): Single<List<DataField>> {
        return networkApi
            .requestDataFields(endpoint.url())
    }

    fun saveDataFeilds(dataFields: List<DataField>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getDataFields(): Single<List<DataField>> {
        return SingleFromCallable { throw NoDataFieldsException() }
    }
}