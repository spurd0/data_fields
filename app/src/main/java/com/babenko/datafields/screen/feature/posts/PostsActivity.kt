package com.babenko.datafields.screen.feature.posts

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.babenko.datafields.R
import com.babenko.datafields.application.util.getViewModel
import com.babenko.datafields.screen.base.BaseActivity
import com.babenko.datafields.screen.feature.posts.adapter.PostsAdapter
import com.babenko.datafields.screen.feature.posts.adapter.RxPagination
import kotlinx.android.synthetic.main.activity_posts_list.*
import kotlinx.android.synthetic.main.layout_progress.*


class PostsActivity : BaseActivity() {
    private lateinit var viewModel: PostsViewModel
    private lateinit var postsAdapter: PostsAdapter

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts_list)
        viewModel = getViewModel()
        initViews()
        with(viewModel) {
            liveData.observe(this@PostsActivity, Observer { update(it!!) })
        }

        RxPagination.with(recyclerView, viewModel.lastPage).observePageChanges().subscribe {
            viewModel.loadPage(false, it + 1)
        }
        viewModel.loadPage(savedInstanceState != null, viewModel.lastPage)
    }

    private fun initViews() {
        postsAdapter = PostsAdapter(this)
        recyclerView.apply {
            adapter = postsAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onSaveInstanceState(state: Bundle) {
        super.onSaveInstanceState(state)
        viewModel.layoutManagerState = recyclerView.layoutManager?.onSaveInstanceState()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        if (viewModel.layoutManagerState != null) {
            recyclerView.layoutManager?.onRestoreInstanceState(viewModel.layoutManagerState)
        }
    }

    private fun update(event: ViewStatePosts) {
        when (event) {
            is ViewStatePosts.LoadingStarted -> progressView.visibility = View.VISIBLE
            is ViewStatePosts.Loaded -> {
                progressView.visibility = View.GONE
                postsAdapter.addData(event.postEntities)
            }
            is ViewStatePosts.Error -> showError(event.message)
        }
    }

    private fun showError(message: String?) {
        progressView.visibility = View.GONE
    }
}