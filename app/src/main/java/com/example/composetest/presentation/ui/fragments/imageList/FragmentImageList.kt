package com.example.composetest.presentation.ui.fragments.imageList

import android.content.*
import android.net.*
import android.os.*
import android.view.*
import android.widget.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.*
import androidx.fragment.app.*
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.compose.*
import androidx.navigation.*
import androidx.navigation.compose.*
import com.example.composetest.R
import com.example.composetest.common.*
import com.example.composetest.presentation.ui.fragments.composeFunction.*
import com.google.accompanist.swiperefresh.*
import kotlinx.coroutines.*


class FragmentImageList : Fragment() {

    private val singleEventObserver = Observer<ImageListSingleEvent?> { state -> eventRender(state) }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as? App)?.listImagesComponent?.inject(this@FragmentImageList)
    }

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val navController = rememberNavController()
                val viewModel = viewModel<ImageListViewModel>()
                    .apply { initialize(this@FragmentImageList) }
                val refreshing = viewModel.isInternet.value
                val state = rememberPullRefreshState(refreshing, {
                    viewModel.isInternet.value = true
                })

                viewModel.singleEventLiveData.observe(viewLifecycleOwner, singleEventObserver)
                NavHost(navController = navController, startDestination = TAG_ALL_IMAGE) {
                    composable(TAG_ALL_IMAGE) {
                        ShowScreen(navController = navController, viewModel = viewModel, isInternet())
                    }
                    composable("$TAG_IMAGE{$TAG_ID}", arguments = listOf(navArgument(TAG_ID) { type = NavType.StringType })) {
                        val userId = it.arguments?.getString(TAG_ID)
                        if (userId != null) {
                            ImagePage(userId, viewModel)
                        }
                    }
                }
                Box(Modifier.pullRefresh(state)) {
                    if (!refreshing) {
                        ShowScreen(navController = navController, viewModel = viewModel, isInternet())
                    }
                    PullRefreshIndicator(refreshing, state, Modifier.align(Alignment.TopCenter))
                }
            }
        }
    }

    private fun eventRender(event: ImageListSingleEvent?) {
        when (event) {
            is ImageListSingleEvent.ShowToast -> {
                Toast.makeText(requireContext(), requireContext().getString(R.string.list_is_empty), Toast.LENGTH_SHORT).show()
            }
            null -> Unit
        }
    }

    private fun isInternet(): Boolean {
        val systemService = requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val internetState = systemService.activeNetworkInfo
        return internetState != null && internetState.isConnectedOrConnecting
    }

    companion object {
        const val TAG_ALL_IMAGE = "allImage"
        const val TAG_IMAGE = "image/"
        const val TAG_ID = "id"
    }
}