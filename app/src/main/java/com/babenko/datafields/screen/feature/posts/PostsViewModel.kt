package com.babenko.datafields.screen.feature.posts

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.Parcelable
import com.babenko.datafields.application.DataFieldsApplication
import com.babenko.datafields.model.entity.PostItem
import com.babenko.datafields.model.interactor.PostsInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@Suppress("ProtectedInFinal")
class PostsViewModel : ViewModel() {
    companion object {
        private const val PAGE_LAST = 1
    }

    @Inject protected lateinit var postsInteractor: PostsInteractor

    val liveData = MutableLiveData<ViewStatePosts>()
    var lastPage = PAGE_LAST
    var postsList = ArrayList<PostItem>()
    var layoutManagerState: Parcelable? = null


    init {
        DataFieldsApplication.appComponent.inject(this)
    }

    fun loadPage(isRestore: Boolean, page: Int) {
        if (isRestore) {
            liveData.value = ViewStatePosts.Loaded(postsList)
            return
        }
        val d = postsInteractor.getPosts(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { liveData.value = ViewStatePosts.LoadingStarted }
            .subscribe(
                {
                    lastPage = page
                    postsList.addAll(it)
                    liveData.value = ViewStatePosts.Loaded(it)
                },
                { liveData.value = ViewStatePosts.Error(it.message) }
            )
    }
}
