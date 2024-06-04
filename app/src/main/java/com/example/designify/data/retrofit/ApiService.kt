package com.example.designify.data.retrofit

import com.example.designify.data.response.UrlResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("photos/random")
    fun getRandomPhoto(
        @Query("client_id") clientId: String,
        @Query("count") count: Int
    ): Call<ArrayList<UrlResponse>>

    @GET("photos/{id}/download")
    fun downloadPhoto(
        @Path("id") id: String
    ): Call<ArrayList<UrlResponse>>

}
