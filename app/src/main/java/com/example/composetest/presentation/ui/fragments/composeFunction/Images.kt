package com.example.composetest.presentation.ui.fragments.composeFunction

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.*
import com.example.composetest.presentation.ui.viewmodel.*

@Composable
fun ImagePage(id: String, viewModel: ListImageViewModel) {
    val item = viewModel.state.value.firstOrNull { it.id == id }
    if(item != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(27.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item.images.original.url?.let { url -> GifDisplay(image = url, modifier = Modifier.fillMaxWidth()) }
            Text(
                text = item.title, fontSize = 15.sp,
                modifier = Modifier.padding(vertical = 5.dp)
            )
            Text(text = item.slug, fontSize = 14.sp)
        }
    } else {
        Text(text = "Не верная ссылка на gif :)", fontSize = 14.sp)

    }
}