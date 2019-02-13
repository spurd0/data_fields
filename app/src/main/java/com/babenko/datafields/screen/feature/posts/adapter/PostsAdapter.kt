package com.babenko.datafields.screen.feature.posts.adapter

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.view.ViewGroup
import com.babenko.datafields.model.entity.PostItem

class PostsAdapter : PagedListAdapter<PostItem, PostViewHolder>(POST_COMPARATOR) {
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onBindViewHolder(
        holder: PostViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isNotEmpty()) {
            val item = getItem(position)
            holder.update(item)
        } else {
            onBindViewHolder(holder, position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder.create(parent)
    }

    companion object {
        val POST_COMPARATOR = object : DiffUtil.ItemCallback<PostItem>() {
            override fun areContentsTheSame(oldItem: PostItem, newItem: PostItem): Boolean =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: PostItem, newItem: PostItem): Boolean =
                oldItem.title == newItem.title && oldItem.body == newItem.body
        }
    }
}