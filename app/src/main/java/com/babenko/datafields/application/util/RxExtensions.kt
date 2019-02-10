package com.babenko.datafields.application.util

import io.reactivex.CompletableTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


fun <T> applyIoMainThreadSchedulersToObservable(): ObservableTransformer<T, T> {
    return ObservableTransformer { upstream ->
        upstream
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}

fun <T> applyIoMainThreadSchedulersToSingle(): SingleTransformer<T, T> {
    return SingleTransformer { upstream ->
        upstream
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}

fun applyIoMainThreadSchedulersToCompletable(): CompletableTransformer {
    return CompletableTransformer { upstream ->
        upstream
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
