package com.ezzyapps.test.repositories.data.remote.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HitsResponse(
    @Json(name = "hits")
    val hits: List<HitDto>,
    @Json(name = "total")
    val total: Int,
    @Json(name = "totalHits")
    val totalHits: Int
)