package com.babenko.datafields.screen.feature.datafields.adapter.holder

import android.support.design.widget.TextInputEditText
import android.support.v7.widget.RecyclerView
import android.view.View
import com.babenko.datafields.R

class DataFieldItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val mFieldValue = itemView.findViewById<View>(R.id.dataFieldValue) as TextInputEditText
}