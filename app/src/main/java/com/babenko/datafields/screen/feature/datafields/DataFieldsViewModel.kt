package com.babenko.datafields.screen.feature.datafields

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.babenko.datafields.application.DataFieldsApplication
import com.babenko.datafields.application.arch.lifecycle.SingleLiveEvent
import com.babenko.datafields.application.util.applyIoMainThreadSchedulersToSingle
import com.babenko.datafields.model.entity.DataField
import com.babenko.datafields.model.interactor.DataFieldsInteractor
import com.babenko.datafields.model.mapper.MapperDataField
import com.babenko.datafields.model.viewobject.DataFieldVo
import timber.log.Timber
import javax.inject.Inject


class DataFieldsViewModel() : ViewModel() {
    @Inject lateinit var dataFieldsInteractor: DataFieldsInteractor
    private val dataFieldsData = MutableLiveData<List<DataFieldVo>>()
    val liveData = SingleLiveEvent<DataFieldsViewState>()

    init {
        DataFieldsApplication.appComponent.inject(this)

        requestFieldsData()
    }

    private fun requestFieldsData() {
        val d = dataFieldsInteractor.getDataFields()
            .compose<List<DataField>>(applyIoMainThreadSchedulersToSingle<List<DataField>>())
            .map { data -> MapperDataField().mapTo(data) }
            .subscribe(this::onDataFieldsObtained, this::onDataFieldsError)
    }

    private fun onDataFieldsObtained(dataFields: List<DataFieldVo>) {
        liveData.value = DataFieldsViewState.Loaded(dataFields)
        dataFieldsData.value = dataFields
    }

    private fun onDataFieldsError(throwable: Throwable) {
        liveData.value = DataFieldsViewState.Error(throwable)
    }

    fun submitButtonPressed() {
        val d = dataFieldsInteractor.checkFields(dataFieldsData.value!!)
            .compose(applyIoMainThreadSchedulersToSingle())
            .subscribe(this::onFieldsChecked)
    }

    private fun onFieldsChecked(correct: Boolean) {
        Timber.d("Checked: $correct")
        liveData.value = DataFieldsViewState.Loaded(dataFieldsData.value!!)
    }
}