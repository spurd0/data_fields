package com.babenko.datafields.model.entity

import com.google.gson.annotations.SerializedName


data class PostItem(
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String
)
