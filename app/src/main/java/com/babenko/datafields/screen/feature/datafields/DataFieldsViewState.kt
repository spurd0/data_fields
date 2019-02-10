package com.babenko.datafields.screen.feature.datafields

import com.babenko.datafields.model.viewobject.DataFieldVo

sealed class DataFieldsViewState {
    class Loaded(val dataFields: List<DataFieldVo>) : DataFieldsViewState()
    class Error(val throwable: Throwable) : DataFieldsViewState()
}