package com.example.testapplication.Network

import com.example.testapplication.Data.MyPostItem
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitApi {

    @GET("posts")
    suspend fun getPost(): Response<List<MyPostItem>>

}