package com.example.composetest.common

import android.app.*
import com.example.composetest.di.components.*
import com.example.composetest.di.modules.*

class App : Application() {
    lateinit var listImagesComponent: ListImagesComponent

    override fun onCreate() {
        super.onCreate()
        listImagesComponent = DaggerListImagesComponent
            .builder()
            .contextModule(ContextModule(this))
            .build()
    }
}