package com.babenko.datafields.screen.feature.datafields

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.babenko.datafields.R
import com.babenko.datafields.application.util.getViewModel
import com.babenko.datafields.model.throwable.NoDataFieldsException
import com.babenko.datafields.model.viewobject.DataFieldVo
import com.babenko.datafields.screen.base.BaseActivity
import com.babenko.datafields.screen.feature.datafields.adapter.DataFieldsAdapter
import kotlinx.android.synthetic.main.activity_data_fields.*

class DataFieldsActivity : BaseActivity() {
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

    private fun initViews() {
        dataFieldsAdapter = DataFieldsAdapter(this)
        dataFieldsRv.adapter = dataFieldsAdapter
        val layoutManager = LinearLayoutManager(this)
        dataFieldsRv.layoutManager = layoutManager
    }

    private fun handleDataFieldsChange(vs: DataFieldsViewState) {
        when (vs) {
            is DataFieldsViewState.Loaded -> showDataFields(vs.dataFields)
            is DataFieldsViewState.Error -> onDataFieldsError(vs.throwable)
        }
    }

    private fun showDataFields(dataFields: List<DataFieldVo>) {
        dataFieldsAdapter.replaceItems(dataFields)
    }

    private fun onDataFieldsError(throwable: Throwable) {
        when (throwable) {
            is NoDataFieldsException -> {
                navigator.navigateToUrlScreen(this, true)
                finish()
            }
        }
    }

//    private fun fillDataFields(dataFields: List<DataField>) {// TODO: 15/05/17 how to correctly make a testing or skip it?
//        for (dataField in dataFields) {
//            when (dataField.type) {
//                TEXT -> dataField.value = "Very-very-very long text"
//                EMAIL -> dataField.value = "foo@java.com"
//                PHONE -> dataField.value = "+79991234200"
//                NUMBER -> dataField.value = "12345"
//                URL -> dataField.value = "ya.ru"
//            }
//        }
//    }

    private fun fieldsSuccessfullyChecked() {
        navigator.navigateToImagesActivity(this)
        finish()
    }
}
