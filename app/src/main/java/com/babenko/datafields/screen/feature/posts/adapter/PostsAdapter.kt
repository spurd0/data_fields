package com.babenko.datafields.screen.feature.posts.adapter

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.babenko.datafields.R
import com.babenko.datafields.application.rest.NetworkState
import com.babenko.datafields.model.entity.PostItem

class PostsAdapter(private val retryCallback: () -> Unit) :
    PagedListAdapter<PostItem, RecyclerView.ViewHolder>(POST_COMPARATOR) {
    companion object {
        val POST_COMPARATOR = object : DiffUtil.ItemCallback<PostItem>() {
            override fun areContentsTheSame(oldItem: PostItem, newItem: PostItem): Boolean =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: PostItem, newItem: PostItem): Boolean =
                oldItem.title == newItem.title && oldItem.body == newItem.body
        }
    }

    private var networkState: NetworkState? = null

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.item_post -> (holder as PostViewHolder).bind(getItem(position))
            R.layout.item_network_state -> (holder as NetworkStateItemViewHolder).bindTo(
                networkState
            )
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isNotEmpty()) {
            val item = getItem(position)
            (holder as PostViewHolder).update(item)
        } else {
            onBindViewHolder(holder, position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_post -> PostViewHolder.create(parent)
            R.layout.item_network_state -> NetworkStateItemViewHolder.create(parent, retryCallback)
            else -> throw IllegalArgumentException("unknown view type $viewType")
        }
    }

    private fun hasExtraRow() = networkState != null && networkState != NetworkState.LOADED

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            R.layout.item_network_state
        } else {
            R.layout.item_post
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    fun setNetworkState(newNetworkState: NetworkState?) {
        val previousState = this.networkState
        val hadExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        val hasExtraRow = hasExtraRow()
        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow && previousState != newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    }
}