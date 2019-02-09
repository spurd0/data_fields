package com.babenko.datafields.application.dagger

import com.babenko.datafields.application.dagger.modules.RetrofitModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [RetrofitModule::class]
)
interface RepositoryComponent {
}