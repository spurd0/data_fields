package com.babenko.datafields.model.entity

import com.google.gson.annotations.SerializedName


data class DataField(
    @SerializedName("id") var id: Int,
    @SerializedName("type") var type: String,
    @SerializedName("placeholder") var placeholder: String,
    @SerializedName("value") var value: String
)
