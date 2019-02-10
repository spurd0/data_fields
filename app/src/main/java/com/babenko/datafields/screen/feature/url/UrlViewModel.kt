package com.babenko.datafields.screen.feature.url

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.widget.Toast

class UrlViewModel(application: Application) : AndroidViewModel(application) {
    private val urlData = MutableLiveData<CharSequence>()

    fun urlChanged(newValue: CharSequence) {
        urlData.value = newValue
    }

    fun requestFieldsClicked() {
        Toast.makeText(getApplication(), urlData.value, Toast.LENGTH_SHORT).show()
    }
}