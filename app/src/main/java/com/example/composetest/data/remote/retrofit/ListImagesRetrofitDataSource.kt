package com.example.composetest.data.remote.retrofit

import com.example.composetest.data.remote.*
import com.example.composetest.domain.repositories.*

class ListImagesRetrofitDataSource(private val listImagesApi: ListImagesApi) : ListImagesRemoteDataSource {
    override suspend fun getListImage() = listImagesApi.getListImage()
}

