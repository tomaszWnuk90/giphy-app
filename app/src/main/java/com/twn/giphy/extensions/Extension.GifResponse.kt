package com.twn.giphy.extensions

import com.twn.giphy.model.GifItem
import com.twn.network.model.GifResponse

fun GifResponse.toGifItem(): GifItem{
    return GifItem(id = id, url = imagesResponse.small.url, title = title, userName = user.userName)
}