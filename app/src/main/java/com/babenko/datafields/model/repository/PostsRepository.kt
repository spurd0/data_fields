package com.babenko.datafields.model.repository

import com.babenko.datafields.model.datasource.rest.NetworkApi
import com.babenko.datafields.model.datasource.rest.constant.RestUrls
import com.babenko.datafields.model.entity.PostItem
import io.reactivex.Single
import javax.inject.Inject

class PostsRepository @Inject constructor(private val networkApi: NetworkApi) {
    fun getPosts(page: Int): Single<List<PostItem>> {
        return networkApi.posts(RestUrls.URL_POSTS, page)
    }
}