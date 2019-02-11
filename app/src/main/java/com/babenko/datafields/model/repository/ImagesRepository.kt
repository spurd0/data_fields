package com.babenko.datafields.model.repository

import com.babenko.datafields.model.datasource.rest.NetworkApi
import com.babenko.datafields.model.datasource.rest.constant.RestUrls.URL_IMAGES
import com.babenko.datafields.model.entity.ImageItem
import io.reactivex.Single
import javax.inject.Inject

class ImagesRepository @Inject constructor(private val networkApi: NetworkApi) {
    fun requestImages(): Single<List<ImageItem>> {
        return networkApi.requestImages(URL_IMAGES)
    }
}