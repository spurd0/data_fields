package com.babenko.datafields.application.dagger

import com.babenko.datafields.application.dagger.modules.PresenterModule
import com.babenko.datafields.screen.feature.splash.SplashViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [PresenterModule::class]
)
interface PresenterComponent {
    fun inject(splashViewModel: SplashViewModel)
}