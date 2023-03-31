package com.example.composetest.presentation.ui.fragments.composeFunction

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.lazy.staggeredgrid.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.*
import com.example.composetest.presentation.ui.fragments.*
import com.example.composetest.presentation.ui.viewmodel.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AllImage(homeViewModel: ListImageViewModel, onClick: (id: String) -> Unit) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2)
    ) {
        items(homeViewModel.listImage.value) { plant ->
            PlantCard(plant.id, plant.images.original.url ?: "", onClick)
        }
    }
}