package com.example.composetest.data.remote

import com.example.composetest.domain.entities.rest.*
import retrofit2.http.*

interface RestService {
    @GET("gifs/trending")
    suspend fun getListImage(
        @Query("api_key") key: String?,
        @Header("X-RapidAPI-Key") apiKey: String,
        @Header("X-RapidAPI-Host") host: String,
    ): ListImageResponse
}