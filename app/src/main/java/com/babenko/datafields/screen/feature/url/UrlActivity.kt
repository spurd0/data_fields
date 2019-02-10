package com.babenko.datafields.screen.feature.url

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.babenko.datafields.R
import com.babenko.datafields.application.util.getViewModel
import com.babenko.datafields.databinding.ActivityUrlBinding
import com.babenko.datafields.screen.base.BaseActivity

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
    }
}
