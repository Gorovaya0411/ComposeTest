package com.example.composetest.presentation.ui.viewmodel

import android.util.*
import androidx.compose.runtime.*
import androidx.lifecycle.*
import com.example.composetest.domain.entities.rest.*
import com.example.composetest.domain.usecase.LoadListImageUseCase
import kotlinx.coroutines.*

class ListImageViewModel(
    private val loadListImageUseCase: LoadListImageUseCase
) : ViewModel() {

    var listImage = mutableStateOf<List<ImageResponse>>(listOf())
    private val _listImage = mutableListOf<ImageResponse>()

    fun getAllImage() {
        loadListImageUseCase.apply {
            viewModelScope.launch(Dispatchers.IO) {
                loadListImageUseCase.getListImage()
                    .onSuccess { listImageResponse ->
                        listImageResponse.data.forEach {
                            _listImage.add(it)
                        }
                        listImage.value = _listImage
                    }
                    .onFailure {
                        Log.e("mistake:", it.toString())
                    }
            }
        }
    }
}