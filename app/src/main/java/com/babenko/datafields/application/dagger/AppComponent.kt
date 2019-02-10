package com.babenko.datafields.application.dagger

import com.babenko.datafields.application.dagger.modules.ContextModule
import com.babenko.datafields.application.dagger.modules.RetrofitModule
import com.babenko.datafields.application.dagger.modules.StorageModule
import com.babenko.datafields.screen.feature.datafields.DataFieldsViewModel
import com.babenko.datafields.screen.feature.splash.SplashViewModel
import com.babenko.datafields.screen.feature.url.UrlViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ContextModule::class, RetrofitModule::class, StorageModule::class]
)
interface AppComponent {
    fun inject(viewModel: SplashViewModel)
    fun inject(viewModel: UrlViewModel)
    fun inject(dataFieldsViewModel: DataFieldsViewModel)
}