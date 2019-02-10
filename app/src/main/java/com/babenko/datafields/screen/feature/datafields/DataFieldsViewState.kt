package com.babenko.datafields.screen.feature.datafields

import com.babenko.datafields.model.viewobject.DataFieldsVo

sealed class DataFieldsViewState {
    class Loaded(val dataFields: DataFieldsVo) : DataFieldsViewState()
    class Error(val throwable: Throwable) : DataFieldsViewState()
    object Checked : DataFieldsViewState()
}