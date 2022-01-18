package com.ezzyapps.test.images.data.remote

import com.ezzyapps.test.images.data.remote.models.HitsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface HitsClient {

    @GET("/")
    fun getImageHits(
        @Query("q", encoded = true) query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("image_type") type: String,
    ): Call<HitsResponse>

}