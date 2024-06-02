package com.twn.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImagesResponse(
    @SerialName("fixed_height_small")
    val small: ImageInfoResponse = ImageInfoResponse(),
    @SerialName("original_still")
    val originalStill: ImageInfoResponse = ImageInfoResponse(),
)
