package com.babenko.datafields.application.util

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import com.babenko.datafields.screen.base.BaseActivity

inline fun <reified T : ViewModel> BaseActivity.getViewModel(): T = ViewModelProviders.of(this)[T::class.java]