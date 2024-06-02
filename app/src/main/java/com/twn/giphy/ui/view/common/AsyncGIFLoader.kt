package com.twn.giphy.ui.view.common

import android.os.Build.VERSION.SDK_INT
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.twn.giphy.extensions.getGifUrl

@Composable
fun AsyncGIFLoader(url: String, modifier: Modifier) {

    val imageRequest = ImageRequest.Builder(LocalContext.current)
        .data(url.getGifUrl())
        .decoderFactory(
            if (SDK_INT >= 28) {
                ImageDecoderDecoder.Factory()
            } else {
                GifDecoder.Factory()
            }
        )
        .crossfade(true)
        .build()

    AsyncImage(
        modifier = modifier,
        model = imageRequest,
        contentDescription = "GIF"
    )
}