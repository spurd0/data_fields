package com.babenko.datafields.screen.navigation

import android.content.Context
import android.content.Intent
import com.babenko.datafields.screen.feature.url.UrlActivity

class Navigator {
    fun navigateToUrlScreen(context: Context) {
        val intent = Intent(context, UrlActivity::class.java)
        context.startActivity(intent)
    }
}