package com.example.composetest.domain.entities.rest

import com.google.gson.annotations.SerializedName

data class Images(
    @SerializedName("downsized_still")
    val downsizedStill: DownsizedStill,
    val original: Original
)