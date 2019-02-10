package com.babenko.datafields.screen.feature.splash

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.babenko.datafields.R
import com.babenko.datafields.application.util.getViewModel
import com.babenko.datafields.screen.base.BaseActivity
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashActivity : BaseActivity() {
    private lateinit var viewModel: SplashViewModel
    private var animation: Animation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash_screen)
        viewModel = getViewModel()
        viewModel.getAnimationData().observe(this, Observer {
            if (it != null) navigateToNextScreen(it)
        })
    }

    override fun onResume() {
        super.onResume()
        if (animation == null) {
            animation = AnimationUtils.loadAnimation(applicationContext, R.anim.animation_center_to_top_movement)
            animation!!.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {

                }

                override fun onAnimationEnd(a: Animation) {
                    viewModel.onAnimationEnd()
                }

                override fun onAnimationRepeat(animation: Animation) {

                }
            })
            splashImageView.startAnimation(animation)
        }
    }

    private fun navigateToNextScreen(screen: SplashViewModel.Screen) {
        when (screen) {
            SplashViewModel.Screen.Login -> navigator.navigateToUrlScreen(this)
            SplashViewModel.Screen.Images -> navigator.navigateToUrlScreen(this)
        }
    }
}