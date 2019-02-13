package com.babenko.datafields.model.interactor

import android.arch.paging.PagedList
import com.babenko.datafields.model.entity.PostItem
import com.babenko.datafields.model.repository.PostsRepository
import javax.inject.Inject

class PostsInteractor @Inject constructor(
    private val postsRepository: PostsRepository
) {
    fun getPosts(): PagedList<PostItem> {
        return postsRepository.getPosts()
    }
}