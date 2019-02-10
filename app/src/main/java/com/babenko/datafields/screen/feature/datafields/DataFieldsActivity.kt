package com.babenko.datafields.screen.feature.datafields

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.babenko.datafields.R
import com.babenko.datafields.application.util.getViewModel
import com.babenko.datafields.model.throwable.NoDataFieldsException
import com.babenko.datafields.model.viewobject.DataFieldsVo
import com.babenko.datafields.screen.base.BaseActivity
import com.babenko.datafields.screen.feature.datafields.adapter.DataFieldsAdapter
import com.babenko.datafields.screen.feature.datafields.adapter.DataFieldsListener
import kotlinx.android.synthetic.main.activity_data_fields.*

class DataFieldsActivity : BaseActivity(), DataFieldsListener {
    companion object {
        private const val TAG = "DataFieldsActivity"
    }

    private lateinit var viewModel: DataFieldsViewModel
    private lateinit var dataFieldsAdapter: DataFieldsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_fields)
        viewModel = getViewModel()
        viewModel.liveData.observe(this, Observer { handleDataFieldsChange(it!!) })
        initViews()
    }

    override fun submitButtonClicked() {
        viewModel.submitButtonPressed()
    }

    private fun initViews() {
        dataFieldsAdapter = DataFieldsAdapter(this, this)
        dataFieldsRv.adapter = dataFieldsAdapter
        val layoutManager = LinearLayoutManager(this)
        dataFieldsRv.layoutManager = layoutManager
    }

    private fun handleDataFieldsChange(vs: DataFieldsViewState) {
        when (vs) {
            is DataFieldsViewState.Loaded -> showDataFields(vs.dataFields)
            is DataFieldsViewState.Error -> onDataFieldsError(vs.throwable)
            is DataFieldsViewState.Checked -> fieldsSuccessfullyChecked()
        }
    }

    private fun showDataFields(dataFields: DataFieldsVo) {
        dataFieldsAdapter.replaceItems(dataFields.fields, dataFields.fieldsCorrect)
    }

    private fun onDataFieldsError(throwable: Throwable) {
        when (throwable) {
            is NoDataFieldsException -> {
                navigator.navigateToUrlScreen(this, true)
                finish()
            }
        }
    }

    private fun fieldsSuccessfullyChecked() {
        navigator.navigateToImagesActivity(this)
        finish()
    }
}
