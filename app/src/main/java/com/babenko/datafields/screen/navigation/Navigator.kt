package com.babenko.datafields.screen.navigation

import android.content.Context
import android.content.Intent
import com.babenko.datafields.screen.feature.datafields.DataFieldsActivity
import com.babenko.datafields.screen.feature.url.UrlActivity

class Navigator {
    fun navigateToUrlScreen(context: Context, newTask: Boolean) {
        val intent = Intent(context, UrlActivity::class.java)
        if (newTask) intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    fun navigateToDataFieldsActivity(context: Context) {
        val intent = Intent(context, DataFieldsActivity::class.java)
        context.startActivity(intent)
    }

    fun navigateToImagesActivity(context: Context) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}