package com.babenko.datafields.screen.feature.posts.adapter

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

const val PAGE_START = 1
const val PAGE_SIZE = 10
const val PAGE_GAP = 5

class RxPagination private constructor(
    private val recyclerView: RecyclerView,
    private var page: Int,
    private val pageSize: Int,
    private val gap: Int
) {
    private var emitted: Boolean = false

    companion object {
        fun with(recyclerView: RecyclerView): RxPagination {
            return RxPagination(
                recyclerView,
                PAGE_START,
                PAGE_SIZE,
                PAGE_GAP
            )
        }

        fun with(recyclerView: RecyclerView, page: Int): RxPagination {
            return RxPagination(
                recyclerView,
                page,
                PAGE_SIZE,
                PAGE_GAP
            )
        }

        fun with(recyclerView: RecyclerView, page: Int, additionalElements: Int): RxPagination {
            return RxPagination(
                recyclerView,
                page,
                PAGE_SIZE + additionalElements,
                PAGE_GAP
            )
        }

    }

    fun observePageChanges(): Observable</*Page*/Int> {
        return Observable.create { emitter ->
            if (recyclerView.layoutManager is LinearLayoutManager || recyclerView.layoutManager is GridLayoutManager) {

                var onScrollListener: RecyclerView.OnScrollListener? = null
                val adapter = recyclerView.adapter
                var dataObserver: RxPaginationAdapterDataObserver? = null
                if (adapter != null) {
                    dataObserver = object : RxPaginationAdapterDataObserver() {
                        override fun onDataChanged() {
                            emitted = false
                        }
                    }

                    adapter.registerAdapterDataObserver(dataObserver)
                }
                val layoutManager = if (recyclerView.layoutManager is LinearLayoutManager) {
                    recyclerView.layoutManager as LinearLayoutManager
                } else {
                    recyclerView.layoutManager as GridLayoutManager
                }
                onScrollListener = object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)

                        if (!emitted) {
                            val maxItems = layoutManager.itemCount % pageSize != 0
                            if (maxItems) {
                                emitted = true
                            } else {
                                val lastItemIndex = layoutManager.itemCount - 1
                                val lastVisiblePosition = layoutManager.findLastVisibleItemPosition()
                                val theEnd = (lastVisiblePosition >= lastItemIndex - gap)

                                if (theEnd) {
                                    page++
                                    emitter.onNext(page)
                                    emitted = true
                                }
                            }
                        }
                    }
                }
                recyclerView.addOnScrollListener(onScrollListener)
                emitter.setDisposable(object : Disposable {
                    private var isDisposed = false
                    override fun isDisposed(): Boolean {
                        return isDisposed
                    }

                    override fun dispose() {
                        recyclerView.removeOnScrollListener(onScrollListener)
                        adapter?.unregisterAdapterDataObserver(dataObserver!!)
                    }
                })
            } else {
                emitter.onError(IllegalLayoutManagerException())
            }
        }
    }

    class IllegalLayoutManagerException : RuntimeException("LayoutManager must be LinearLayoutManager")
}