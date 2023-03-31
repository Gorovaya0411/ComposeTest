package com.example.composetest.domain.entities.rest

data class ImageResponse(
    val slug: String,
    val source: String,
    val title: String,
    val username: String,
    val images: Images,
    val id: String
)