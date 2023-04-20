package com.example.composetest.presentation.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.*
import androidx.fragment.app.Fragment
import com.example.composetest.common.App
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.*
import androidx.navigation.compose.*
import com.example.composetest.presentation.ui.fragments.composeFunction.*

import com.example.composetest.presentation.ui.viewmodel.ListImageViewModel

class FragmentImageList : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as? App)?.listImagesComponent?.inject(this@FragmentImageList)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val navController = rememberNavController()
                val viewModel = viewModel<ListImageViewModel>()
                    .apply { initialize(this@FragmentImageList) }

                NavHost(navController = navController, startDestination = TAG_ALL_IMAGE) {
                    composable(TAG_ALL_IMAGE) {
                        AllImagePage(viewModel) { id ->
                            navController.navigate("$TAG_IMAGE$id")
                        }
                    }
                    composable("$TAG_IMAGE{$TAG_ID}", arguments = listOf(navArgument(TAG_ID) { type = NavType.StringType })) {
                        val userId = it.arguments?.getString(TAG_ID)
                        if (userId != null) {
                            ImagePage(userId, viewModel)
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val TAG_ALL_IMAGE = "allImage"
        const val TAG_IMAGE = "image/"
        const val TAG_ID = "id"
    }
}