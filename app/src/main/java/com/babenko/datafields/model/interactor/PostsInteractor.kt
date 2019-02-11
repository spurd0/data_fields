package com.babenko.datafields.model.interactor

import com.babenko.datafields.model.datasource.rest.constant.RestUrls.URL_POSTS
import com.babenko.datafields.model.entity.PostItem
import com.babenko.datafields.model.repository.PostsRepository
import io.reactivex.Single
import javax.inject.Inject

class PostsInteractor @Inject constructor(
    private val postsRepository: PostsRepository
) {
    fun getPosts(page: Int): Single<List<PostItem>> {
        return postsRepository.getPosts(page)
    }
}