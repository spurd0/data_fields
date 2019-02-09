package com.babenko.datafields.screen.base

import android.support.v7.app.AppCompatActivity
import com.babenko.datafields.screen.navigation.Navigator

abstract class BaseActivity : AppCompatActivity() {
    protected val navigator: Navigator = Navigator()
}
