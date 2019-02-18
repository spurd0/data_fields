package com.babenko.datafields.application.util

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.view.View
import com.babenko.datafields.screen.base.BaseActivity

inline fun <reified T : ViewModel> BaseActivity.getViewModel(): T = ViewModelProviders.of(this)[T::class.java]

fun toVisibility(constraint: Boolean): Int {
    return if (constraint) {
        View.VISIBLE
    } else {
        View.GONE
    }
}