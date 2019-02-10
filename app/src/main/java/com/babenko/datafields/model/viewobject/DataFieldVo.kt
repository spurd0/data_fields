package com.babenko.datafields.model.viewobject

data class DataFieldVo(
    val id: Int,
    val type: String,
    val placeholder: String,
    var value: String?
)
