package com.ezzyapps.test.images.domain.models

data class ImagePreview(

    val id: Long,
    val userName: String,
    val url: String,
    val width: Int,
    val height: Int,
    val tags: List<String>,

)