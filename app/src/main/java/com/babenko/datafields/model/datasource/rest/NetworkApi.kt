package com.babenko.datafields.model.datasource.rest

import com.babenko.datafields.model.entity.DataField
import com.babenko.datafields.model.entity.ImageItem
import com.babenko.datafields.model.entity.PostItem
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface NetworkApi {
    @GET
    fun requestDataFields(@Url url: String): Single<List<DataField>>

    @GET
    fun requestImages(@Url url: String): Single<List<ImageItem>>

    @GET
    fun posts(@Url url: String, @Query("_page") page: Int): Single<List<PostItem>>
}
