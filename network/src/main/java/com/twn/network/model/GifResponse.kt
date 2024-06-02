package com.twn.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GifResponse(
    @SerialName("id")
    val id: String = "",
    @SerialName("bitly_url")
    val url: String = "",
    @SerialName("title")
    val title: String = "",
    @SerialName("images")
    val imagesResponse: ImagesResponse = ImagesResponse(),
    @SerialName("user")
    val user: UserResponse = UserResponse()
)
