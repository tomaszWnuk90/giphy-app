package com.twn.network.core

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Inject

internal class ApiClientFactory @Inject constructor(private val okHttpClientFactory: OkHttpClientFactory) {


    fun <T> buildService(apiUrl: String, clazz: Class<T>, apiKey : String): T {
        val client: OkHttpClient = okHttpClientFactory.buildOkHttpClient(
            OkHttpClientFactory.DEFAULT_CONNECT_TIMEOUT_SECONDS,
            apiKey,
        )

        val networkJson = Json { ignoreUnknownKeys = true }
        val retrofit = Retrofit.Builder()
            .baseUrl(apiUrl)
            .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()))
            .client(client)
            .build()

        return retrofit.create(clazz)
    }
}