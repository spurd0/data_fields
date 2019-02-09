package com.babenko.datafields.screen.feature.splash

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.babenko.datafields.application.DataFieldsApplication
import com.babenko.datafields.model.interactor.LaunchInteractor
import javax.inject.Inject

class SplashViewModel : ViewModel() {
    enum class Screen { Login, Images }

    @Inject lateinit var launchInteractor: LaunchInteractor
    private var isFirstLaunch = false
    private val screenData = MutableLiveData<Screen>()

    init {
        DataFieldsApplication.presenterComponent.inject(this)

        isFirstLaunch = launchInteractor.isFirstLaunch
    }

    fun onAnimationEnd() {
        screenData.postValue(if (isFirstLaunch) Screen.Login else Screen.Images)
    }

    fun getAnimationData(): LiveData<Screen> {
        return screenData
    }
}