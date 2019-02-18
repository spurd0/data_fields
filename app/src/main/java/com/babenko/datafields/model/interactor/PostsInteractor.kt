package com.babenko.datafields.model.interactor

import com.babenko.datafields.model.entity.PostItem
import com.babenko.datafields.model.repository.posts.Listing
import com.babenko.datafields.model.repository.posts.PostsRepository
import javax.inject.Inject

class PostsInteractor @Inject constructor(
    private val postsRepository: PostsRepository
) {
    fun getPosts(): Listing<PostItem> {
        return postsRepository.getPosts()
    }
}