package com.example.composetest.presentation.ui.fragments.composeFunction

import android.content.*
import android.net.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.res.*
import androidx.core.content.ContentProviderCompat.*
import androidx.navigation.*
import com.example.composetest.R
import com.example.composetest.presentation.ui.fragments.imageList.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ShowScreen(navController: NavHostController, viewModel: ImageListViewModel, isInternet: Boolean) {
    if (isInternet) {
        AllImagePage(homeViewModel = viewModel, onClick = { id ->
            navController.navigate("${FragmentImageList.TAG_IMAGE}$id")
        })
    } else {
        Box {
            LazyColumn(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
                items(1) {
                    ListItem { Text(text = stringResource(id = R.string.is_no_internet), modifier = Modifier.align(Alignment.Center)) }
                }
            }
        }
    }
}