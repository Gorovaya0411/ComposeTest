package com.example.composetest.presentation.ui.fragments.composeFunction

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.*
import com.example.composetest.presentation.ui.viewmodel.*

@Composable
fun Image(id: String, viewModel: ListImageViewModel) {
    viewModel.listImage.value.forEach {
        if (id == it.id) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(27.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                it.images.original.url?.let { url -> GifDisplay(image = url, modifier = Modifier.fillMaxWidth()) }
                Text(
                    text = it.title, fontSize = 15.sp,
                    modifier = Modifier.padding(vertical = 5.dp)
                )
                Text(
                    text = it.slug, fontSize = 14.sp
                )
            }
        }
    }
}