package com.twn.network.di

import com.twn.network.BuildConfig
import com.twn.network.Repository
import com.twn.network.RepositoryImp
import com.twn.network.core.ApiClientFactory
import com.twn.network.core.GiphtyApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    internal fun provideGiphtyApiClient(apiClientFactory: ApiClientFactory): GiphtyApiClient {
        return apiClientFactory.buildService(BuildConfig.giphyUrl, GiphtyApiClient::class.java, BuildConfig.giphyApiKey)
    }

    @Provides
    @Singleton
    internal fun provideRepository(giphtyApiClient: GiphtyApiClient): Repository {
        return RepositoryImp(giphtyApiClient)
    }
}