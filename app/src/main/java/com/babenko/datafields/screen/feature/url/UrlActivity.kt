package com.babenko.datafields.screen.feature.url

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.View
import com.babenko.datafields.R
import com.babenko.datafields.application.util.getViewModel
import com.babenko.datafields.databinding.ActivityUrlBinding
import com.babenko.datafields.model.throwable.IncorrectUrlException
import com.babenko.datafields.model.throwable.NoConnectionException
import com.babenko.datafields.screen.base.BaseActivity
import kotlinx.android.synthetic.main.activity_url.*
import kotlinx.android.synthetic.main.layout_progress.*
import timber.log.Timber

class UrlActivity : BaseActivity() {
    companion object {
        private const val TAG = "UrlActivity"
    }

    private lateinit var viewModel: UrlViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityUrlBinding = DataBindingUtil.setContentView(this, R.layout.activity_url)
        viewModel = getViewModel()
        binding.viewmodel = viewModel

        with(viewModel) {
            liveData.observe(this@UrlActivity, Observer { update(it!!) })
        }
    }

    private fun update(event: UrlViewState) {
        when (event) {
            UrlViewState.LoadingStarted -> onLoadingStarted()
            is UrlViewState.Loaded -> onLoaded()
            is UrlViewState.Error -> onError(event.throwable)
        }
    }

    private fun onLoadingStarted() {
        progressView.visibility = View.VISIBLE
        requestUrlEditText.isEnabled = false
    }

    private fun onLoaded() {
        progressView.visibility = View.GONE
        requestUrlEditText.isEnabled = true
        Timber.d("Loaded")
    }

    private fun onError(throwable: Throwable) {
        progressView.visibility = View.GONE
        requestUrlEditText.isEnabled = true

        val error = when (throwable) {
            is NoConnectionException -> {
                getString(R.string.error_no_connection)
            }
            is IncorrectUrlException -> {
                getString(R.string.error_incorrect_url)
            }
            else -> {
                getString(R.string.error_general_server)
            }
        }
        requestUrlEditText.error = error
    }
}
