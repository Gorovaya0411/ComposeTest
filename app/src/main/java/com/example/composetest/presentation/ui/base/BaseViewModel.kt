package com.example.composetest.presentation.ui.base

import androidx.lifecycle.*

abstract class BaseViewModel<SE> : ViewModel() {

    private val singleEvent = SingleLiveEvent<SE?>()
    val singleEventLiveData: LiveData<SE?> = singleEvent

    fun clearSingleEvent() {
        singleEvent.postValue(null)
    }

    protected fun SE.postValue() {
        singleEvent.postValue(this)
    }
}