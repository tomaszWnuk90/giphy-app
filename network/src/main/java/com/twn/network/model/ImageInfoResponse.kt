package com.twn.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageInfoResponse(
    @SerialName("width")
    val width: Int = 0,
    @SerialName("height")
    val height: Int = 0,
    @SerialName("url")
    val url: String = "",
)