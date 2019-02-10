package com.babenko.datafields.model.interactor

import com.babenko.datafields.model.datasource.rest.config.ServerEndpoint
import com.babenko.datafields.model.datasource.rest.constant.RestUrls
import com.babenko.datafields.model.entity.ImageItem
import com.babenko.datafields.model.repository.ImagesRepository
import io.reactivex.Single
import javax.inject.Inject

class ImagesInteractor @Inject constructor(
    private val imagesRepository: ImagesRepository
) {
    companion object {
        private val TAG = "ImagesInteractor"
    }

    fun requestPicturesList(): Single<List<ImageItem>> {
        return imagesRepository.requestImages(object : ServerEndpoint {
            override fun url(): String {
                return RestUrls.URL_IMAGES
            }
        })
    }
}
