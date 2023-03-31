package com.example.composetest.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.*
import androidx.fragment.app.Fragment
import com.example.composetest.common.App
import com.example.composetest.domain.usecase.LoadListImageUseCase
import androidx.compose.material.*
import androidx.navigation.*
import androidx.navigation.compose.*
import com.example.composetest.presentation.ui.fragments.composeFunction.*

import com.example.composetest.presentation.ui.viewmodel.ListImageViewModel
import javax.inject.Inject

class FragmentImageList : Fragment() {

    @Inject
    lateinit var listImageUseCase: LoadListImageUseCase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            (requireActivity().application as App).listImagesComponent.inject(this@FragmentImageList)

            val viewModel = ListImageViewModel(listImageUseCase)
            viewModel.getAllImage()
            setContent {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = TAG_ALL_IMAGE
                ) {
                    composable(TAG_ALL_IMAGE) {
                        Surface(color = MaterialTheme.colors.background) {
                            AllImage(viewModel) { id ->
                                navController.navigate("$TAG_IMAGE$id")
                            }
                        }
                    }
                    composable(
                        "$TAG_IMAGE{$TAG_ID}",
                        arguments = listOf(navArgument(TAG_ID) { type = NavType.StringType })
                    ) {
                        val userId = it.arguments?.getString(TAG_ID)
                        if (userId != null) {
                            Image(userId, viewModel)
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