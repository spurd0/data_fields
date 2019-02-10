package com.babenko.datafields.screen.feature.datafields.adapter

import android.content.Context
import android.support.design.widget.TextInputEditText
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.util.SparseIntArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.babenko.datafields.R
import com.babenko.datafields.application.util.getInputHint
import com.babenko.datafields.application.util.getInputType
import com.babenko.datafields.model.viewobject.DataFieldsVo
import com.babenko.datafields.screen.feature.datafields.adapter.holder.DataFieldFooterViewHolder
import com.babenko.datafields.screen.feature.datafields.adapter.holder.DataFieldHeaderViewHolder


class DataFieldsAdapter(
    private val context: Context,
    private val dataFieldsListener: DataFieldsListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val TYPE_HEADER = 1
        private const val TYPE_DATA_FIELD = 2
        private const val TYPE_FOOTER = 3

        private const val POSITION_DATA_FIELDS = 1
    }

    private val containerLayoutRes = object : SparseIntArray() {
        init {
            put(TYPE_HEADER, R.layout.item_data_field_header)
            put(TYPE_DATA_FIELD, R.layout.item_data_field)
            put(TYPE_FOOTER, R.layout.item_data_field_footer)
        }
    }
    private val inflater = LayoutInflater.from(context)
    private val items = ArrayList<AdapterItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> DataFieldHeaderViewHolder(
                inflater.inflate(
                    containerLayoutRes.get(viewType),
                    parent,
                    false
                )
            )
            TYPE_DATA_FIELD -> {
                val view = inflater.inflate(
                    containerLayoutRes.get(viewType),
                    parent,
                    false
                )
                val vh = DataFieldItemViewHolder(view)
                return vh
            }
            else -> DataFieldFooterViewHolder(
                inflater.inflate(
                    containerLayoutRes.get(viewType),
                    parent,
                    false
                ),
                dataFieldsListener
            )
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].itemViewType
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataElement = items[position]
        val viewType = dataElement.itemViewType
        when (viewType) {
            TYPE_HEADER -> onBindHeader(holder as DataFieldHeaderViewHolder, dataElement as DataFieldHeaderAdapterItem)
            TYPE_DATA_FIELD -> onBindDataField(holder as DataFieldItemViewHolder, position)
        }
    }

    private fun onBindHeader(
        holder: DataFieldHeaderViewHolder,
        item: DataFieldHeaderAdapterItem
    ) {
        holder.validationErrorTv.visibility = if (item.fieldsCorrect) View.GONE else View.VISIBLE
    }

    private fun onBindDataField(
        holder: DataFieldItemViewHolder,
        position: Int
    ) {
        val item = items[position] as DataFieldAdapterItem
        val dataElement = item.dataField
        val value = dataElement.value
        holder.fieldValue.tag = position;
        holder.fieldValue.setText(value)
        holder.fieldValue.hint = getInputHint(dataElement.type, context)
        holder.fieldValue.inputType = getInputType(dataElement.type)
        if (dataElement.error) {
            holder.fieldValue.error = context.getString(R.string.data_field_incorrect)
        } else {
            holder.fieldValue.error = null
        }
    }

    fun replaceItems(
        dataFields: List<DataFieldsVo.DataFieldVo>,
        fieldsCorrect: Boolean
    ) {
        items.clear()
        items.add(DataFieldHeaderAdapterItem(fieldsCorrect))
        for (dataField in dataFields) {
            items.add(POSITION_DATA_FIELDS, DataFieldAdapterItem(dataField))
        }
        items.add(DataFieldFooterAdapterItem())
        notifyDataSetChanged()
    }

    interface AdapterItem {
        val itemViewType: Int
    }

    data class DataFieldAdapterItem(val dataField: DataFieldsVo.DataFieldVo) :
        AdapterItem {
        override val itemViewType = TYPE_DATA_FIELD
    }

    private data class DataFieldHeaderAdapterItem(val fieldsCorrect: Boolean) : AdapterItem {
        override val itemViewType = TYPE_HEADER
    }

    private class DataFieldFooterAdapterItem() : AdapterItem {
        override val itemViewType = TYPE_FOOTER
    }

    private inner class DataFieldsTextWatcher(private val editText: EditText) : TextWatcher {

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            val position = editText.tag as Int
            val item = items[position] as DataFieldAdapterItem
            val dataElement = item.dataField
            dataElement.value = s.toString()
        }

        override fun afterTextChanged(editable: Editable) {}
    }

    private inner class DataFieldItemViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        val fieldValue = itemView.findViewById<View>(R.id.dataFieldValue) as TextInputEditText

        init {
            fieldValue.addTextChangedListener(DataFieldsTextWatcher(fieldValue))
        }
    }
}