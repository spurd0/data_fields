package com.babenko.datafields.screen.feature.datafields

import com.babenko.datafields.model.entity.DataField

sealed class DataFieldsViewState {
    class Loaded(val dataFields: List<DataField>) : DataFieldsViewState()
    class Error(val throwable: Throwable) : DataFieldsViewState()
}