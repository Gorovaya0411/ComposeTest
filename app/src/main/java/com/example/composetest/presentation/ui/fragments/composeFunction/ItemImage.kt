package com.example.composetest.presentation.ui.fragments.composeFunction

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.*

@Composable
fun PlantCard(id: String, image: String, onClick: (id: String) -> Unit) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .clickable { onClick(id) },
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
    ) {
        Column(
            modifier = Modifier
                .background(Color.Cyan)
                .aspectRatio(1f)
        ) {
            GifDisplay(image = image, modifier = Modifier.fillMaxSize())
        }
    }
}