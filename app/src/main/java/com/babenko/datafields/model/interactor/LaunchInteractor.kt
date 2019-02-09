package com.babenko.datafields.model.interactor

import com.babenko.datafields.application.DataFieldsApplication
import com.babenko.datafields.model.repository.AppInfoRepository
import javax.inject.Inject

class LaunchInteractor @Inject constructor(
) {
    @Inject lateinit var appInfoRepository: AppInfoRepository

    init {
        DataFieldsApplication.modelComponent.inject(this)
    }

    val isFirstLaunch: Boolean
        get() {
            val timeStamp = appInfoRepository.firstLaunchTimeStamp
            return if (timeStamp == null) {
                appInfoRepository.firstLaunchTimeStamp = System.currentTimeMillis()
                true
            } else {
                false
            }
        }
}