package com.ezzyapps.test.repositories.data.remote

import com.ezzyapps.test.repositories.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RemoteClient {

    private const val baseUrl = "https://pixabay.com/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val headersInterceptor = Interceptor { chain ->
        chain.request().let { request ->
            val url = request.url.newBuilder()
                .addPathSegment("api")
                .addQueryParameter("key", BuildConfig.TOKEN)
                .build()
            request.newBuilder().url(url)
        }.build().run { chain.proceed(this) }
    }

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(headersInterceptor)
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(httpClient)
        .build()

    fun <T> create(clazz: Class<T>): T = retrofit.create(clazz)
}