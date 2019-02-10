package com.babenko.datafields.screen.feature.datafields.adapter

import android.text.Editable
import android.text.TextWatcher
import com.babenko.datafields.model.entity.DataField


class DataFieldsTextWatcher(private val dataField: DataField) : TextWatcher {

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        dataField.value = s.toString()
    }

    override fun afterTextChanged(editable: Editable) {}
}