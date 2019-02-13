package com.babenko.datafields.screen.feature.posts.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.babenko.datafields.R
import com.babenko.datafields.model.entity.PostItem
import timber.log.Timber

class PostViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
    companion object {
        fun create(
            parent: ViewGroup
        ): PostViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_post, parent, false)
            return PostViewHolder(view)
        }
    }

    private val bodyText: TextView = itemView.findViewById(R.id.bodyTv)
    private val titleText: TextView = itemView.findViewById(R.id.titleTv)
    private var post: PostItem? = null

    fun bind(post: PostItem?) {
        this.post = post
        titleText.text = post?.title ?: itemView.context.resources.getString(R.string.loading_title)
        bodyText.text = post?.body ?: itemView.context.resources.getString(R.string.loading_body)
    }

    fun update(item: PostItem?) {
        Timber.d("Update!")
    }
}