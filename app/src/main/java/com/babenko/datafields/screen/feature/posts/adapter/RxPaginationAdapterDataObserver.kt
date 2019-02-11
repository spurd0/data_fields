package com.babenko.datafields.screen.feature.posts.adapter

import android.support.v7.widget.RecyclerView

abstract class RxPaginationAdapterDataObserver : RecyclerView.AdapterDataObserver() {
    override fun onChanged() {
        onDataChanged()
    }

    override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
        onDataChanged()
    }

    override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
        onDataChanged()
    }

    override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
        onDataChanged()
    }

    override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
        onDataChanged()
    }

    override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
        onDataChanged()
    }

    abstract fun onDataChanged()
}