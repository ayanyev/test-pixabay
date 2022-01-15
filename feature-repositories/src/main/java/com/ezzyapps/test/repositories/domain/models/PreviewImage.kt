package com.ezzyapps.test.repositories.domain.models

data class PreviewImage(

    val id: Long,
    val userName: String,
    val url: String,
    val width: Int,
    val height: Int,
    val tags: List<String>,

)