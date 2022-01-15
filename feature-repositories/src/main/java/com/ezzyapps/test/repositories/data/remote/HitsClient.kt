package com.ezzyapps.test.repositories.data.remote

import com.ezzyapps.test.repositories.data.remote.models.HitsResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface HitsClient {

    @GET("/")
    fun getPublicRepositories(
        @Query("q", encoded = true) query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("image_type") type: String,
    ): Call<HitsResponse>

}