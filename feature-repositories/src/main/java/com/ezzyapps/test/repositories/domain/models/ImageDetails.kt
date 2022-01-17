package com.ezzyapps.test.repositories.domain.models

data class ImageDetails(

    val id: Long,
    val userName: String,
    val url: String,
    val width: Int,
    val height: Int,
    val tags: List<String>,
    val likesCount: Int,
    val downloadsCount: Int,
    val commentsCount: Int

)