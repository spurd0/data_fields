package com.babenko.datafields.screen.feature.url

import android.os.Bundle
import com.babenko.datafields.R
import com.babenko.datafields.screen.base.BaseActivity

class UrlActivity : BaseActivity() {
    companion object {
        private const val TAG = "UrlActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_url)

        initViews()
    }

    private fun initViews() {
    }
}
