package com.babenko.datafields.model.repository.posts

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import com.babenko.datafields.application.rest.NetworkState

data class Listing<T>(
    val pagedList: PagedList<T>,
    val networkState: LiveData<NetworkState>
)