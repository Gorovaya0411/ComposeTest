package com.example.composetest.domain.usecase

import com.example.composetest.data.extension.*
import com.example.composetest.data.repositories.ListImagesRepository
import com.example.composetest.domain.entities.rest.*

class LoadListImageUseCase(
    private val listImageRepo: ListImagesRepository
) {
    suspend fun getListImage(): Result<ListImageResponse> =
        resultOf {
            listImageRepo.getListImage()
        }
}
