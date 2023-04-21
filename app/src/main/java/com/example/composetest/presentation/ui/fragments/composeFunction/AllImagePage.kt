package com.example.composetest.presentation.ui.fragments.composeFunction

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.lazy.staggeredgrid.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.res.*
import androidx.compose.ui.unit.*
import com.example.composetest.presentation.ui.fragments.*
import com.example.composetest.presentation.ui.fragments.imageList.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AllImagePage(homeViewModel: ImageListViewModel, onClick: (id: String) -> Unit) {
    LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(2)) {
        items(items = homeViewModel.state.value, key = { it.id.hashCode() }) { plant ->
            Surface(color = MaterialTheme.colors.background) {
                PlantCard(plant.id, plant.images.original.url ?: "", onClick)
            }
        }
    }
}