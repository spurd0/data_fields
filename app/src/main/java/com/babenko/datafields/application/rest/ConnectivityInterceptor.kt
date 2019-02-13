package com.babenko.datafields.application.rest

import android.net.ConnectivityManager
import com.babenko.datafields.model.throwable.NoConnectionException
import okhttp3.Interceptor
import okhttp3.Response

class ConnectivityInterceptor(private val cm: ConnectivityManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (cm.activeNetworkInfo == null) {
            throw NoConnectionException()
        }

        val request = chain.request()
        return chain.proceed(request)
    }
}
