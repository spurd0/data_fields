package com.babenko.datafields.model.interactor

import android.util.Patterns
import com.babenko.datafields.model.datasource.rest.config.ServerEndpoint
import com.babenko.datafields.model.repository.DataFieldsRepository
import com.babenko.datafields.model.throwable.IncorrectUrlException
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject


class DataFieldsInteractor @Inject constructor(
    private val dataFieldsRepository: DataFieldsRepository
) {
    fun requestDataFields(url: String): Completable {
        return getEndpoint(url)
            .flatMapCompletable {
                dataFieldsRepository.requestDataFields(it)
                    .ignoreElement()
            }
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
}