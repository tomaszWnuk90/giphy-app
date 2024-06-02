package com.twn.giphy.extensions

fun String.getGifUrl(): String {
    return this.split("?").first()
}