package com.babenko.datafields.application.dagger

import com.babenko.datafields.application.dagger.modules.ContextModule
import com.babenko.datafields.application.dagger.modules.RetrofitModule
import com.babenko.datafields.application.dagger.modules.StorageModule
import com.babenko.datafields.model.repository.AppInfoRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ContextModule::class, RetrofitModule::class, StorageModule::class]
)
interface RepositoryComponent {
    fun inject(appInfoRepository: AppInfoRepository)

}