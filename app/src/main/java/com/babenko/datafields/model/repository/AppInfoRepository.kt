package com.babenko.datafields.model.repository

import com.babenko.datafields.model.datasource.storage.Prefs
import javax.inject.Inject

class AppInfoRepository @Inject constructor(private val prefs: Prefs) {
    var firstLaunchTimeStamp: Long?
        get() = prefs.firstLaunchTimeStamp
        set(value) {
            prefs.firstLaunchTimeStamp = value
        }
}
