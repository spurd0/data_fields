package com.babenko.datafields.screen.feature.posts

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations.map
import android.arch.lifecycle.Transformations.switchMap
import android.arch.lifecycle.ViewModel
import com.babenko.datafields.application.DataFieldsApplication
import com.babenko.datafields.model.entity.PostItem
import com.babenko.datafields.model.interactor.PostsInteractor
import com.babenko.datafields.model.repository.posts.Listing
import timber.log.Timber
import javax.inject.Inject

class PostsViewModel : ViewModel() {
    @Inject protected lateinit var postsInteractor: PostsInteractor

    init {
        DataFieldsApplication.appComponent.inject(this)
    }

    private val postsLiveData = PostsLiveData()

    val posts = map(postsLiveData) { it.pagedList }!!
    val networkState = switchMap(postsLiveData) { it.networkState }!!

    fun retry() {
        Timber.d("retry")
        //todo implement it
    }

    inner class PostsLiveData : LiveData<Listing<PostItem>>() {
        override fun onActive() {
            value = postsInteractor.getPosts()
        }
    }
}