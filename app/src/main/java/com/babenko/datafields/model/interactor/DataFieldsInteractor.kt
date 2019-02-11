package com.babenko.datafields.model.interactor

import android.text.TextUtils
import android.util.Patterns
import com.babenko.datafields.model.datasource.rest.constant.RestConsts.EMAIL
import com.babenko.datafields.model.datasource.rest.constant.RestConsts.NUMBER
import com.babenko.datafields.model.datasource.rest.constant.RestConsts.PHONE
import com.babenko.datafields.model.datasource.rest.constant.RestConsts.TEXT
import com.babenko.datafields.model.datasource.rest.constant.RestConsts.URL
import com.babenko.datafields.model.entity.DataField
import com.babenko.datafields.model.repository.DataFieldsRepository
import com.babenko.datafields.model.throwable.IncorrectUrlException
import com.babenko.datafields.model.viewobject.DataFieldsVo
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.internal.operators.completable.CompletableFromAction
import io.reactivex.internal.operators.single.SingleFromCallable
import java.util.regex.Pattern
import javax.inject.Inject


class DataFieldsInteractor @Inject constructor(
    private val dataFieldsRepository: DataFieldsRepository
) {
    companion object {
        private const val REGEX_PHONE = "^[+7]{2}\\d{10}$"
        private const val REGEX_NUMBER = "^\\d{1,5}$"
        private const val NUMBER_RANGE_START = 11
        private const val NUMBER_RANGE_END = 29
    }

    fun requestDataFields(url: String): Completable {
        return getEndpoint(url)
            .flatMapCompletable {
                dataFieldsRepository.requestDataFields(it)
                    .flatMapCompletable(this::saveDataFields)
            }
    }

    private fun saveDataFields(dataFields: List<DataField>): Completable {
        return CompletableFromAction { dataFieldsRepository.saveDataFields(dataFields) }
    }

    private fun getEndpoint(url: String): Single<String> {
        return Single.fromCallable {
            if (!Patterns.WEB_URL.matcher(url).matches()) {
                throw IncorrectUrlException()
            }
            return@fromCallable url
        }
    }

    fun getDataFields(): Single<List<DataField>> {
        return dataFieldsRepository.getDataFields()
    }

    fun checkFields(values: DataFieldsVo): Single<Boolean> {
        return SingleFromCallable {
            var allIsCorrect = true
            for (value in values.fields) {
                val correct = isDataFieldCorrect(value.value, value.type)
                value.error = !correct
                if (!correct) {
                    allIsCorrect = false
                }
            }
            values.fieldsCorrect = allIsCorrect
            return@SingleFromCallable allIsCorrect
        }
    }

    private fun isDataFieldCorrect(data: String?, type: String): Boolean {
        if (TextUtils.isEmpty(data)) return false
        when (type) {
            TEXT -> {
                val length = data!!.length
                return length in NUMBER_RANGE_START..NUMBER_RANGE_END
            }
            EMAIL -> return android.util.Patterns.EMAIL_ADDRESS.matcher(data).matches()
            PHONE -> {
                val phonePattern = Pattern.compile(REGEX_PHONE)
                return phonePattern.matcher(data).matches()
            }
            NUMBER -> {
                val numberPattern = Pattern.compile(REGEX_NUMBER)
                return numberPattern.matcher(data).matches()
            }
            URL -> {
                return Patterns.WEB_URL.matcher(data).matches()
            }
            else -> {
                throw IllegalArgumentException("Unknown type")
            }
        }
    }
}