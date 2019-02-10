package com.babenko.datafields.application.dagger.modules

import android.content.Context
import android.net.ConnectivityManager
import com.babenko.datafields.model.datasource.rest.ConnectivityInterceptor
import com.babenko.datafields.model.datasource.rest.NetworkApi
import com.babenko.datafields.model.datasource.rest.config.ServerEndpoint
import com.babenko.datafields.model.datasource.rest.config.SimpleServerEndpoint
import com.babenko.datafields.model.datasource.rest.constant.RestOptions
import com.google.gson.FieldNamingPolicy
import com.google.gson.FieldNamingStrategy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Field
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class RetrofitModule {
    @Singleton
    @Provides
    fun provideUsersRestClient(retrofit: Retrofit): NetworkApi {
        return retrofit.create(NetworkApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(endpoint: ServerEndpoint, gson: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(endpoint.url())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideServerEndpoint(): ServerEndpoint {
        return SimpleServerEndpoint()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        connectivityInterceptor: ConnectivityInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(connectivityInterceptor)
            .retryOnConnectionFailure(true)
            .connectTimeout(RestOptions.TIMEOUT_CONNECTION_SECONDS, TimeUnit.SECONDS)
            .readTimeout(RestOptions.TIMEOUT_READ_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(RestOptions.TIMEOUT_WRITE_SECONDS, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideConverterFactory(gson: Gson): Converter.Factory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        return GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .setFieldNamingStrategy(CustomFieldNamingPolicy())
            .setPrettyPrinting()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .serializeNulls()
            .create()
    }

    @Provides
    @Singleton
    fun provideConnectivityInterceptor(context: Context): ConnectivityInterceptor {
        return ConnectivityInterceptor(
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        )
    }

    private class CustomFieldNamingPolicy : FieldNamingStrategy {
        override fun translateName(field: Field): String {
            var name = FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES.translateName(field)
            if (name == "value")
                return "default_value"
            if (name == "album_id")
                return "albumId"
            return if (name == "thumbnail_url") "thumbnailUrl" else name
        }
    }
}
