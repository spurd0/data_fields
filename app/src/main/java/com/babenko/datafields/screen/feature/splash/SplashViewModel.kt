package com.babenko.datafields.screen.feature.splash

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class SplashViewModel : ViewModel() {
    private val animationData = MutableLiveData<Boolean>()

    fun onAnimationEnd() {
        animationData.postValue(true)
    }

    fun getAnimationData(): LiveData<Boolean> {
        return animationData
    }
}