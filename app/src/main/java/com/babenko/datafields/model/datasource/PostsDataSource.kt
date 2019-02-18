package com.babenko.datafields.model.datasource

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PositionalDataSource
import com.babenko.datafields.application.rest.NetworkApi
import com.babenko.datafields.application.rest.NetworkState
import com.babenko.datafields.application.rest.constant.RestConsts.POSTS_LOAD_SIZE
import com.babenko.datafields.application.rest.constant.RestUrls.URL_POSTS
import com.babenko.datafields.application.util.applyIoMainThreadSchedulersToSingle
import com.babenko.datafields.model.entity.PostItem
import timber.log.Timber

class PostsDataSource(
    private val networkApi: NetworkApi
) : PositionalDataSource<PostItem>() {
    val networkState = MutableLiveData<NetworkState>()

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<PostItem>) {
        networkState.postValue(NetworkState.LOADING)
        val d = networkApi.requestPosts(URL_POSTS, params.requestedStartPosition / POSTS_LOAD_SIZE)
            .compose(applyIoMainThreadSchedulersToSingle())
            .subscribe({
                callback.onResult(it, 0)
                networkState.postValue(NetworkState.LOADED)
            }, {
                Timber.e(it)
                networkState.postValue(NetworkState.error(it.message ?: "unknown err"))
            })
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<PostItem>) {
        networkState.postValue(NetworkState.LOADING)
        val d = networkApi.requestPosts(URL_POSTS, params.startPosition / POSTS_LOAD_SIZE)
            .compose(applyIoMainThreadSchedulersToSingle())
            .subscribe({
                callback.onResult(it)
                networkState.postValue(NetworkState.LOADED)
            }, {
                Timber.e(it)
                networkState.postValue(NetworkState.error(it.message ?: "unknown err"))
            })
    }
}