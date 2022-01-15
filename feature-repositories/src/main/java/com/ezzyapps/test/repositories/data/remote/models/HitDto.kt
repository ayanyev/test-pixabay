package com.ezzyapps.test.repositories.data.remote.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HitDto(
    @Json(name = "comments")
    val comments: Int,
    @Json(name = "downloads")
    val downloads: Int,
    @Json(name = "fullHDURL")
    val fullHDURL: String?,
    @Json(name = "id")
    val id: Long,
    @Json(name = "imageHeight")
    val imageHeight: Int,
    @Json(name = "imageSize")
    val imageSize: Int,
    @Json(name = "imageURL")
    val imageURL: String?,
    @Json(name = "imageWidth")
    val imageWidth: Int,
    @Json(name = "largeImageURL")
    val largeImageURL: String,
    @Json(name = "likes")
    val likes: Int,
    @Json(name = "pageURL")
    val pageURL: String,
    @Json(name = "previewHeight")
    val previewHeight: Int,
    @Json(name = "previewURL")
    val previewURL: String,
    @Json(name = "previewWidth")
    val previewWidth: Int,
    @Json(name = "tags")
    val tags: String,
    @Json(name = "type")
    val type: String,
    @Json(name = "user")
    val user: String,
    @Json(name = "user_id")
    val userId: Int,
    @Json(name = "userImageURL")
    val userImageURL: String,
    @Json(name = "views")
    val views: Int,
    @Json(name = "webformatHeight")
    val webFormatHeight: Int,
    @Json(name = "webformatURL")
    val webFormatURL: String,
    @Json(name = "webformatWidth")
    val webFormatWidth: Int
)