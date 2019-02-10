package com.babenko.datafields.model.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "data_field")
data class DataField(
    @PrimaryKey @SerializedName("id") var id: Int,
    @ColumnInfo(name = "type") @SerializedName("type") var type: String,
    @ColumnInfo(name = "placeholder") @SerializedName("placeholder") var placeholder: String
)
