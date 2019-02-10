package com.babenko.datafields.screen.feature.datafields.adapter.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.babenko.datafields.R

class DataFieldHeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val validationErrorTv = view.findViewById<TextView>(R.id.validationErrorTv)!!
}