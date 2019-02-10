package com.babenko.datafields.screen.feature.url

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.babenko.datafields.application.DataFieldsApplication
import com.babenko.datafields.application.util.applyIoMainThreadSchedulersToCompletable
import com.babenko.datafields.model.datasource.rest.constant.RestUrls.DEBUG_URL_FIELDS
import com.babenko.datafields.model.interactor.DataFieldsInteractor
import javax.inject.Inject

class UrlViewModel : ViewModel() {
    @Inject lateinit var dataFieldsInteractor: DataFieldsInteractor
    val urlData = MutableLiveData<CharSequence>()
    val liveData = MutableLiveData<UrlViewState>()

    init {
        DataFieldsApplication.appComponent.inject(this)

        urlData.value = DEBUG_URL_FIELDS
    }

    fun urlChanged(newValue: CharSequence) {
        urlData.value = newValue
    }

    fun requestFieldsClicked() {
        val d = dataFieldsInteractor.requestDataFields(urlData.value.toString())
            .compose(applyIoMainThreadSchedulersToCompletable())
            .doOnSubscribe { liveData.value = UrlViewState.LoadingStarted }
            .subscribe(
                { liveData.value = UrlViewState.Loaded() },
                { liveData.value = UrlViewState.Error(it) }
            )
    }
}