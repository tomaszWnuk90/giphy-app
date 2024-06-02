package com.twn.network.core

import android.os.Debug
import com.twn.network.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Inject

internal class OkHttpClientFactory @Inject constructor(){

    companion object {
        const val DEFAULT_CONNECT_TIMEOUT_SECONDS: Int = 10
        private const val DEFAULT_READ_TIMEOUT_SECONDS: Int = 10
    }

    fun buildOkHttpClient(
        connectTimeout: Int,
        apiKey : String
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            if(BuildConfig.DEBUG) {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }
        return OkHttpClient.Builder().apply {
            connectTimeout(connectTimeout.toLong(), TimeUnit.SECONDS)
            readTimeout(DEFAULT_READ_TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)

            addInterceptor(loggingInterceptor)
            addInterceptor(ApiKeyInterceptor(apiKey))


        }.build()
    }

    private class ApiKeyInterceptor(val apiKey: String) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val original = chain.request()
            val originalHttpUrl: HttpUrl = original.url

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", apiKey)
                .build()

            val request = original.newBuilder()
                .url(url)
                .build()

            return chain.proceed(request)
        }
    }
}