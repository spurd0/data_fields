package com.babenko.datafields.screen.feature.images

import com.babenko.datafields.model.entity.ImageItem

sealed class ImagesViewState {
    object LoadingStarted : ImagesViewState()
    class Loaded(val imagesEntities: List<ImageItem>) : ImagesViewState()
    class Error(val throwable: Throwable) : ImagesViewState()
}