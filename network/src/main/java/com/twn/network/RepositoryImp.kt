package com.twn.network

import com.twn.network.core.CallWrapper.selfCall
import com.twn.network.core.GiphtyApiClient
import com.twn.network.model.GifResponse
import javax.inject.Inject
import javax.inject.Singleton
@Singleton
internal class RepositoryImp @Inject constructor(private val giphtyApiClient: GiphtyApiClient) :
    Repository {
    override suspend fun downloadGifs() =
        selfCall{giphtyApiClient.getTrendingGif().body()?.gifs}


    override suspend fun searchForGifs(text: String) =
        selfCall{giphtyApiClient.searchGifs(text).body()?.gifs}

}