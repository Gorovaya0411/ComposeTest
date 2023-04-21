package com.example.composetest.presentation.ui.fragments.imageList

import androidx.compose.runtime.*
import androidx.fragment.app.*
import androidx.lifecycle.*
import com.example.composetest.common.*
import com.example.composetest.domain.entities.rest.*
import com.example.composetest.domain.usecase.*
import com.example.composetest.presentation.ui.base.*
import kotlinx.coroutines.*
import javax.inject.*

open class ImageListViewModel : BaseViewModel<ImageListSingleEvent>() {

    @Inject
    lateinit var loadListImageUseCase: LoadListImageUseCase

    private val _state = mutableStateOf<List<ImageResponse>>(listOf())
    val state = _state

    private val _isInternet = mutableStateOf(true)
    val isInternet = _isInternet

    //TODO: так не правильно, надо делать через inject или еще как-то
    fun initialize(fragment: Fragment) {
        (fragment.activity?.application as App).listImagesComponent.inject(this)

        viewModelScope.launch(Dispatchers.IO) {
            loadListImageUseCase.getImages()
                .onSuccess { images ->
                    if (images.isEmpty()) {
                        ImageListSingleEvent.ShowToast.postValue()
                    } else {
                        _state.value = images
                        _isInternet.value = false
                    }
                }
                .onFailure {
                    withContext(Dispatchers.Main) {
                        _isInternet.value = false
                        _state.value = emptyList()
                    }
                }
        }
    }
}