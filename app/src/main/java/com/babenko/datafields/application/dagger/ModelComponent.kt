package com.babenko.datafields.application.dagger

import com.babenko.datafields.application.dagger.modules.ContextModule
import com.babenko.datafields.application.dagger.modules.ModelModule
import com.babenko.datafields.model.interactor.LaunchInteractor
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ContextModule::class, ModelModule::class]
)
interface ModelComponent {
    fun inject(launchInteractor: LaunchInteractor)

}