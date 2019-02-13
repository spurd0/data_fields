package com.babenko.datafields.model.repository

import android.arch.paging.PagedList
import android.os.Handler
import android.os.Looper
import android.support.annotation.NonNull
import com.babenko.datafields.application.rest.NetworkApi
import com.babenko.datafields.application.rest.constant.RestConsts.POSTS_LOAD_SIZE
import com.babenko.datafields.model.datasource.PostsDataSource
import com.babenko.datafields.model.entity.PostItem
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject


class PostsRepository @Inject constructor(private val networkApi: NetworkApi) {
    fun getPosts(): PagedList<PostItem> {
        val dataSource = PostsDataSource(networkApi)

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(POSTS_LOAD_SIZE)
            .build()

        return PagedList.Builder<Int, PostItem>(dataSource, config)
            .setNotifyExecutor(MainThreadExecutor())
            .setFetchExecutor(Executors.newSingleThreadExecutor())
            .build()
    }

    private class MainThreadExecutor : Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())

        override fun execute(@NonNull command: Runnable) {
            mainThreadHandler.post(command)
        }
    }
}