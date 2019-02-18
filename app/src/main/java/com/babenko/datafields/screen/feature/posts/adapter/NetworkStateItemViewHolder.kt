package com.babenko.datafields.screen.feature.posts.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.babenko.datafields.R
import com.babenko.datafields.application.rest.NetworkState
import com.babenko.datafields.application.rest.Status
import com.babenko.datafields.application.util.toVisibility

class NetworkStateItemViewHolder constructor(
    view: View,
    private val retryCallback: () -> Unit
) : RecyclerView.ViewHolder(view) {
    companion object {
        fun create(parent: ViewGroup, retryCallback: () -> Unit): NetworkStateItemViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_network_state, parent, false)
            return NetworkStateItemViewHolder(view, retryCallback)
        }
    }

    private val progressBar = view.findViewById<ProgressBar>(R.id.progress_bar)
    private val retry = view.findViewById<Button>(R.id.retry_button)
    private val errorMsg = view.findViewById<TextView>(R.id.error_msg)

    init {
        retry.setOnClickListener {
            retryCallback()
        }
    }

    fun bindTo(networkState: NetworkState?) {
        progressBar.visibility = toVisibility(networkState?.status == Status.RUNNING)
        retry.visibility = toVisibility(networkState?.status == Status.FAILED)
        errorMsg.visibility = toVisibility(networkState?.msg != null)
        errorMsg.text = networkState?.msg
    }
}