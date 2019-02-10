package com.babenko.datafields.screen.feature.images

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.babenko.datafields.R
import com.babenko.datafields.application.util.getViewModel
import com.babenko.datafields.model.entity.ImageItem
import com.babenko.datafields.model.throwable.NoConnectionException
import com.babenko.datafields.screen.base.BaseActivity
import com.babenko.datafields.screen.feature.images.adapter.ImagesAdapter
import com.babenko.datafields.screen.feature.images.adapter.ImagesListener
import kotlinx.android.synthetic.main.activity_images.*
import kotlinx.android.synthetic.main.layout_progress.*

class ImagesActivity : BaseActivity() {
    private lateinit var viewModel: ImagesViewModel
    private lateinit var imagesAdapter: ImagesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_images)
        viewModel = getViewModel()
        viewModel.liveData.observe(this, Observer { handleImagesChange(it!!) })
        initViews()
    }

    private fun handleImagesChange(vs: ImagesViewState) {
        when (vs) {
            is ImagesViewState.LoadingStarted -> onLoadingStarted()
            is ImagesViewState.Loaded -> showImages(vs.imagesEntities)
            is ImagesViewState.Error -> onLoadImagesError(vs.throwable)
        }
    }

    private fun onLoadingStarted() {
        progressView.visibility = View.VISIBLE
    }

    private fun showImages(imagesEntities: List<ImageItem>) {
        progressView.visibility = View.GONE
        if (imagesEntities.isEmpty()) {
            setImagesErrorVisible()
        } else {
            showImagesList(imagesEntities)
        }
    }

    private fun onLoadImagesError(throwable: Throwable) {
        progressView.visibility = View.GONE
        setImagesErrorVisible()
        val error = when (throwable) {
            is NoConnectionException -> {
                getString(R.string.error_no_connection)
            }
            else -> {
                getString(R.string.error_general_server)
            }
        }
        imagesErrorTv.text = error
    }

    private fun initViews() {
        imagesAdapter = ImagesAdapter(this, object : ImagesListener {
            override fun itemClicked(imageUrl: String) {
            }
        })
        imagesRecyclerView.layoutManager = LinearLayoutManager(this)
        imagesRecyclerView.adapter = imagesAdapter
    }

    private fun setImagesErrorVisible() {
        imagesErrorTv.visibility = View.VISIBLE
        imagesRecyclerView.visibility = View.GONE
    }

    private fun showImagesList(images: List<ImageItem>) {
        imagesErrorTv.visibility = View.GONE
        imagesRecyclerView.visibility = View.VISIBLE

        imagesAdapter.replaceImages(images)
    }
}