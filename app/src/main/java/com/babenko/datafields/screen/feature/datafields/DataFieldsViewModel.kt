package com.babenko.datafields.screen.feature.datafields

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.babenko.datafields.application.DataFieldsApplication
import com.babenko.datafields.application.arch.lifecycle.SingleLiveEvent
import com.babenko.datafields.application.util.applyIoMainThreadSchedulersToCompletable
import com.babenko.datafields.application.util.applyIoMainThreadSchedulersToSingle
import com.babenko.datafields.model.entity.DataField
import com.babenko.datafields.model.interactor.DataFieldsInteractor
import timber.log.Timber
import javax.inject.Inject


class DataFieldsViewModel() : ViewModel() {
    @Inject lateinit var dataFieldsInteractor: DataFieldsInteractor
    private val dataFieldsData = MutableLiveData<List<DataField>>()
    val liveData = SingleLiveEvent<DataFieldsViewState>()

    init {
        DataFieldsApplication.appComponent.inject(this)
    }

    private fun requestFieldsData() {
        val d = dataFieldsInteractor.getDataFields()
            .compose<List<DataField>>(applyIoMainThreadSchedulersToSingle<List<DataField>>())
            .subscribe(this::onDataFieldsObtained, this::onDataFieldsError)
    }

    private fun onDataFieldsObtained(dataFields: List<DataField>) {
        liveData.value = DataFieldsViewState.Loaded(dataFields)
        dataFieldsData.value = dataFields
    }

    private fun onDataFieldsError(throwable: Throwable) {
        liveData.value = DataFieldsViewState.Error(throwable)
    }

    fun submitButtonPressed() {
        val d = dataFieldsInteractor.checkFields(dataFieldsData.value!!)
            .compose(applyIoMainThreadSchedulersToCompletable())
            .subscribe { Timber.d("Checked") }
    }
}