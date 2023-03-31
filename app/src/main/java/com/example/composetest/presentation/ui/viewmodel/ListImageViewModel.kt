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

    var counter = mutableStateOf<List<ImageResponse>>(listOf())
    private val _counter = mutableListOf<ImageResponse>()

    fun getAllImage() {
        loadListImageUseCase.apply {
            viewModelScope.launch(Dispatchers.IO) {
                loadListImageUseCase.getListImage()
                    .onSuccess { listImageResponse ->
                        listImageResponse.data.forEach {
                            _counter.add(it)
                        }
                        counter.value = _counter
                    }
                    .onFailure {
                        Log.e("mistake:", it.toString())
                    }
            }
        }
    }
}