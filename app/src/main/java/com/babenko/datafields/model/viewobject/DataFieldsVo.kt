package com.babenko.datafields.model.viewobject

data class DataFieldsVo(
    val fields: List<DataFieldVo>,
    var fieldsCorrect: Boolean
) {
    data class DataFieldVo(
        val id: Int,
        val type: String,
        val placeholder: String,
        var value: String?,
        var error: Boolean = false
    )
}
