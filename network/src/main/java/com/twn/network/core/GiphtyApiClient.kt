package com.twn.network.core

import com.twn.network.model.GifListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface GiphtyApiClient {

    @GET("trending")
    suspend fun getTrendingGif(): Response<GifListResponse>

    @GET("search")
    suspend fun searchGifs(@Query("q") term: String): Response<GifListResponse>
}