package com.babenko.datafields.screen.feature.posts

import com.babenko.datafields.model.entity.PostItem

sealed class ViewStatePosts {
    object LoadingStarted : ViewStatePosts()
    class Loaded(val postEntities: List<PostItem>) : ViewStatePosts()
    class Error(val message: String?) : ViewStatePosts()
}