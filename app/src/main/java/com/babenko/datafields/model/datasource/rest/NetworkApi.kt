package com.babenko.datafields.model.datasource.rest

import com.babenko.datafields.model.entity.DataField
import com.babenko.datafields.model.entity.ImageItem
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface NetworkApi {
    @GET
    fun requestDataFields(@Url url: String): Single<Response<Array<DataField>>>

    @GET
    fun requestImages(@Url url: String): Single<Response<Array<ImageItem>>>
}
