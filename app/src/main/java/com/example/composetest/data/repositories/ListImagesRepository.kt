package com.example.composetest.data.repositories


import com.example.composetest.domain.repositories.*

class ListImagesRepository(
    private val remoteDataSource: ListImagesRemoteDataSource
) {
    suspend fun getListImage() = remoteDataSource.getListImage()
}

