package com.babenko.datafields.screen.feature.posts

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.os.Bundle
import com.babenko.datafields.R
import com.babenko.datafields.application.util.getViewModel
import com.babenko.datafields.screen.base.BaseActivity
import com.babenko.datafields.screen.feature.posts.adapter.PostsAdapter
import kotlinx.android.synthetic.main.activity_posts_list.*


class PostsActivity : BaseActivity() {
    private lateinit var model: PostsViewModel
    private lateinit var adapter: PostsAdapter

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts_list)
        model = getViewModel()
        initViews()
        model.networkState.observe(this, Observer {
            adapter.setNetworkState(it)
        })
        model.posts.observe(this, Observer {
            adapter.submitList(it)
        })
    }

    private fun initViews() {
        initAdapter()
    }

    private fun initAdapter() {
        adapter = PostsAdapter { model.retry() }
        recyclerView.adapter = adapter
    }
}