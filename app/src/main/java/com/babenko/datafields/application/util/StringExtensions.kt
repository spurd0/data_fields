package com.babenko.datafields.application.util

import android.content.Context
import android.text.InputType
import com.babenko.datafields.R
import com.babenko.datafields.model.datasource.rest.constant.RestConsts.EMAIL
import com.babenko.datafields.model.datasource.rest.constant.RestConsts.NUMBER
import com.babenko.datafields.model.datasource.rest.constant.RestConsts.PHONE
import com.babenko.datafields.model.datasource.rest.constant.RestConsts.TEXT
import com.babenko.datafields.model.datasource.rest.constant.RestConsts.URL

fun getInputType(type: String): Int {
    return when (type) {
        TEXT -> InputType.TYPE_CLASS_TEXT
        EMAIL -> InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        PHONE -> InputType.TYPE_CLASS_PHONE
        NUMBER -> InputType.TYPE_CLASS_NUMBER
        URL -> InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS
        else -> throw IllegalArgumentException("Unknown type")
    }
}

fun getInputHint(type: String, context: Context): String {
    return when (type) {
        TEXT -> context.getString(R.string.data_field_text_hint)
        EMAIL -> context.getString(R.string.data_field_email_hint)
        PHONE -> context.getString(R.string.data_field_phone_hint)
        NUMBER -> context.getString(R.string.data_field_number_hint)
        URL -> context.getString(R.string.data_field_url_hint)
        else -> throw IllegalArgumentException("Unknown type")
    }
}