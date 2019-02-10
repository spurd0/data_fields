package com.babenko.datafields.model.interactor

import com.babenko.datafields.model.repository.AppInfoRepository
import javax.inject.Inject

class LaunchInteractor @Inject constructor(
    private val appInfoRepository: AppInfoRepository
) {
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