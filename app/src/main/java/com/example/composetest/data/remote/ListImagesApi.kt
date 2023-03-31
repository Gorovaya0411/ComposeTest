package com.example.composetest.data.remote

import com.example.composetest.data.*


class ListImagesApi(private val restService: RestService) {
    suspend fun getListImage() =
        restService.getListImage(KEY, API_KEY, HOST)
}