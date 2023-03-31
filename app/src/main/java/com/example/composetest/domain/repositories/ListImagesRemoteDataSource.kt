package com.example.composetest.domain.repositories

import com.example.composetest.domain.entities.rest.*

interface ListImagesRemoteDataSource {
    suspend fun getListImage(): ListImageResponse
}