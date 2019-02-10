package com.babenko.datafields.model.interactor

import android.util.Patterns
import com.babenko.datafields.model.datasource.rest.config.ServerEndpoint
import com.babenko.datafields.model.entity.DataField
import com.babenko.datafields.model.repository.DataFieldsRepository
import com.babenko.datafields.model.throwable.IncorrectUrlException
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.internal.operators.completable.CompletableFromAction
import javax.inject.Inject


class DataFieldsInteractor @Inject constructor(
    private val dataFieldsRepository: DataFieldsRepository
) {
    fun requestDataFields(url: String): Completable {
        return getEndpoint(url)
            .flatMapCompletable {
                dataFieldsRepository.requestDataFields(it)
                    .flatMapCompletable(this::saveDataFields)
            }
    }

    private fun saveDataFields(dataFields: List<DataField>): Completable {
        return CompletableFromAction { dataFieldsRepository.saveDataFeilds(dataFields) }
    }

    private fun getEndpoint(url: String): Single<ServerEndpoint> {
        return Single.fromCallable {
            if (!Patterns.WEB_URL.matcher(url).matches()) {
                throw IncorrectUrlException()
            }
            return@fromCallable object : ServerEndpoint {
                override fun url(): String {
                    return url
                }
            }
        }
    }

    fun getDataFields(): Single<List<DataField>> {
        return dataFieldsRepository.getDataFields()
    }

    fun checkFields(valuesList: List<DataField>): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}