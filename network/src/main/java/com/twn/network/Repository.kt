package com.twn.network

import com.twn.network.model.GifResponse

interface Repository {
    suspend fun downloadGifs(): ApiOperation<List<GifResponse>?>

    suspend fun searchForGifs(text: String): ApiOperation<List<GifResponse>?>
}