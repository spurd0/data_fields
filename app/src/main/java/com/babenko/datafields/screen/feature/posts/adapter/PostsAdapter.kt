package com.babenko.datafields.screen.feature.posts.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.babenko.datafields.R
import com.babenko.datafields.model.entity.PostItem

class PostsAdapter(context: Context) : RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {
    private val inflater = LayoutInflater.from(context)
    var posts: ArrayList<PostItem> = ArrayList()

    fun addData(posts: List<PostItem>) {
        this.posts.addAll(posts)
        notifyDataSetChanged()
    }

    fun setData(posts: List<PostItem>) {
        this.posts.clear()
        this.posts.addAll(posts)
        notifyDataSetChanged()
    }

    fun getItem(position: Int): PostItem {
        return posts[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, p: Int): PostViewHolder {
        val holder = PostViewHolder(inflater.inflate(R.layout.item_post, parent, false))
        holder.itemView.setOnClickListener { v ->
        }
        return holder
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    inner class PostViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val bodyText: TextView = itemView.findViewById(R.id.bodyTv)
        private val titleText: TextView = itemView.findViewById(R.id.titleTv)

        fun bindView(post: PostItem) {
            post.apply {
                bodyText.text = body
                titleText.text = title
            }
        }
    }
}