package com.babenko.datafields.application.storage

import android.content.Context

class Prefs(private val context: Context) {
    private fun getSharedPreferences(prefsName: String) =
        context.getSharedPreferences(prefsName, Context.MODE_PRIVATE)


    private val APP_DATA = "app_data"
    private val KEY_FIRST_LAUNCH_TIME = "launch_ts"
    private val appPrefs by lazy { getSharedPreferences(APP_DATA) }

    var firstLaunchTimeStamp: Long?
        get() = appPrefs.getLong(KEY_FIRST_LAUNCH_TIME, 0).takeIf { it > 0 }
        set(value) {
            appPrefs.edit().putLong(KEY_FIRST_LAUNCH_TIME, value ?: 0).apply()
        }
}
