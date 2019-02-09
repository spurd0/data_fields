package com.babenko.datafields.model.repository

import com.babenko.datafields.application.DataFieldsApplication
import com.babenko.datafields.model.datasource.storage.Prefs
import javax.inject.Inject

class AppInfoRepository {
    @Inject lateinit var prefs: Prefs

    init {
        DataFieldsApplication.repositoryComponent.inject(this)
    }

    var firstLaunchTimeStamp: Long?
        get() = prefs.firstLaunchTimeStamp
        set(value) {
            prefs.firstLaunchTimeStamp = value
        }
}
