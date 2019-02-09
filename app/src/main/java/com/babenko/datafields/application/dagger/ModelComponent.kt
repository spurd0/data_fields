package com.babenko.datafields.application.dagger

import com.babenko.datafields.application.dagger.modules.ContextModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ContextModule::class]
)
interface ModelComponent {

}
