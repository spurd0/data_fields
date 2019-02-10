package com.babenko.datafields.screen.feature.datafields.adapter.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.babenko.datafields.R
import com.babenko.datafields.screen.feature.datafields.adapter.DataFieldsListener

class DataFieldFooterViewHolder(
    view: View,
    dataFieldsListener: DataFieldsListener
) : RecyclerView.ViewHolder(view) {
    init {
        view.findViewById<View>(R.id.submitFieldsButton).setOnClickListener { dataFieldsListener.submitButtonClicked() }
    }
}
