package com.twn.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GifListResponse(
    @SerialName("data")val gifs: List<GifResponse>)
