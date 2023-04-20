package com.example.composetest.presentation.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composetest.common.App
import com.example.composetest.domain.entities.rest.ImageResponse
import com.example.composetest.domain.usecase.LoadListImageUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListImageViewModel : ViewModel() {

    @Inject
    lateinit var loadListImageUseCase: LoadListImageUseCase

    private val _state = mutableStateOf<List<ImageResponse>>(listOf())
    val state = _state

    //TODO: так не правильно, надо делать через inject или еще как-то
    fun initialize(fragment: Fragment) {

        (fragment.activity?.application as App).listImagesComponent.inject(this)

        viewModelScope.launch(Dispatchers.IO) {
            loadListImageUseCase.getImages()
                .onSuccess { images ->
                    _state.value = images
                }
                .onFailure {
                    state.value = emptyList()
                }
        }
    }
}