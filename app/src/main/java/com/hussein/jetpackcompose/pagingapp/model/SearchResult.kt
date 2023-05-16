package com.hussein.jetpackcompose.pagingapp.model

import kotlinx.serialization.*

@Serializable
data class SearchResult(
    val images: List<UnsplashImage>
)
