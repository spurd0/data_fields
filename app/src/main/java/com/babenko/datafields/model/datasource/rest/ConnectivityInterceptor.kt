package com.babenko.datafields.model.datasource.rest

import android.net.ConnectivityManager
import com.babenko.datafields.model.throwable.NoConnectionException
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ConnectivityInterceptor(private val cm: ConnectivityManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (cm.activeNetworkInfo == null) {
            throw NoConnectionException()
        }

        val request = chain.request()
        return chain.proceed(request)
    }
}
