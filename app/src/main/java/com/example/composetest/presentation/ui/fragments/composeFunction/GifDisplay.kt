package com.example.composetest.presentation.ui.fragments.composeFunction

import android.os.*
import androidx.compose.foundation.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.layout.*
import androidx.compose.ui.platform.*
import coil.*
import coil.compose.*
import coil.decode.*

@Composable
fun GifDisplay(image: String, modifier: Modifier = Modifier) {
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    Image(
        painter = rememberAsyncImagePainter(image, imageLoader),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}