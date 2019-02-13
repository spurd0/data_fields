package com.babenko.datafields.screen.feature.posts

import android.arch.lifecycle.ViewModel
import com.babenko.datafields.application.DataFieldsApplication
import com.babenko.datafields.model.interactor.PostsInteractor
import javax.inject.Inject

class PostsViewModel : ViewModel() {
    @Inject protected lateinit var postsInteractor: PostsInteractor

    init {
        DataFieldsApplication.appComponent.inject(this)
    }

    val posts = postsInteractor.getPosts()
}
