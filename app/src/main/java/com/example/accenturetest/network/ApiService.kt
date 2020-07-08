package com.example.accenturetest.network

import com.example.accenturetest.data.ResponseData
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("facts.json")
    fun getResponseData(): Call<ResponseData>
}