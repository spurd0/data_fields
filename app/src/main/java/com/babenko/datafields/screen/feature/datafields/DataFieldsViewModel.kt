package com.babenko.datafields.screen.feature.datafields

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.babenko.datafields.application.DataFieldsApplication
import com.babenko.datafields.application.arch.lifecycle.SingleLiveEvent
import com.babenko.datafields.application.util.applyIoMainThreadSchedulersToSingle
import com.babenko.datafields.model.datasource.rest.constant.RestConsts
import com.babenko.datafields.model.entity.DataField
import com.babenko.datafields.model.interactor.DataFieldsInteractor
import com.babenko.datafields.model.mapper.MapperDataField
import com.babenko.datafields.model.viewobject.DataFieldsVo
import javax.inject.Inject


class DataFieldsViewModel() : ViewModel() {
    @Inject lateinit var dataFieldsInteractor: DataFieldsInteractor
    private val dataFieldsData = MutableLiveData<DataFieldsVo>()
    val liveData = SingleLiveEvent<DataFieldsViewState>()

    init {
        DataFieldsApplication.appComponent.inject(this)

        requestFieldsData()
    }

    private fun requestFieldsData() {
        val d = dataFieldsInteractor.getDataFields()
            .compose<List<DataField>>(applyIoMainThreadSchedulersToSingle<List<DataField>>())
            .map { data -> MapperDataField().mapTo(data) }
            .map { data -> DataFieldsVo(data, true) }
            .map { data -> fillDataFields(data) }
            .subscribe(this::onDataFieldsObtained, this::onDataFieldsError)
    }

    private fun fillDataFields(dataFields: DataFieldsVo): DataFieldsVo {
        for (dataField in dataFields.fields) {
            when (dataField.type) {
                RestConsts.TEXT -> dataField.value = "Very-very-very long text"
                RestConsts.EMAIL -> dataField.value = "foo@java.com"
                RestConsts.PHONE -> dataField.value = "+79991234200"
                RestConsts.NUMBER -> dataField.value = "12345"
                RestConsts.URL -> dataField.value = "ya.ru"
            }
        }
        return dataFields
    }

    private fun onDataFieldsObtained(dataFields: DataFieldsVo) {
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
        liveData.value = DataFieldsViewState.Loaded(dataFieldsData.value!!)
        if (correct) liveData.value = DataFieldsViewState.Checked
    }
}