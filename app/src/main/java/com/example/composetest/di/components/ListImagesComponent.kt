package com.example.composetest.di.components

import com.example.composetest.di.modules.*
import com.example.composetest.presentation.ui.fragments.*
import com.example.composetest.presentation.ui.viewmodel.ListImageViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ListImagesModule::class, ContextModule::class, NetworkModule::class])
interface ListImagesComponent {
    fun inject(fragment: FragmentImageList)

    fun inject(viewModel: ListImageViewModel)

}