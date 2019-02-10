package com.babenko.datafields.model.repository

import com.babenko.datafields.model.datasource.rest.NetworkApi
import com.babenko.datafields.model.datasource.rest.config.ServerEndpoint
import com.babenko.datafields.model.entity.DataField
import io.reactivex.Single
import javax.inject.Inject

class DataFieldsRepository @Inject constructor(private val networkApi: NetworkApi) {
    fun requestDataFields(endpoint: ServerEndpoint): Single<List<DataField>> {
        return networkApi
            .requestDataFields(endpoint.url())
    }
}