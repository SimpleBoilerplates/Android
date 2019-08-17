package com.me.booksdemoandroid.shared.repositories

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.me.booksdemoandroid.BuildConfig
import com.me.booksdemoandroid.shared.pref.Pref

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object RetrofitFactory {

    private val authInterceptor = Interceptor { chain ->

        val accessToken = Pref.token

        val newUrl = chain.request().url
            .newBuilder()
            //.addQueryParameter("api_key", AppConstants.tmdbApiKey)
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .addHeader("Authorization", "Bearer $accessToken")
            .build()

        chain.proceed(newRequest)
    }


    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val headersInterceptor = Interceptor { chain ->
        chain.proceed(
            chain.request().newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .build()
        )
    }

    //Not logging the authkey if not debug
    private val client =
        if (BuildConfig.DEBUG) {
            OkHttpClient().newBuilder()
                .addInterceptor(authInterceptor)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(headersInterceptor)
                .build()
        } else {
            OkHttpClient().newBuilder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(authInterceptor)
                .addInterceptor(headersInterceptor)
                .build()
        }


    fun retrofit(baseUrl: String): Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

}
