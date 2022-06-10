package com.example.stockscrypto

import com.example.models.APIResponse
import com.example.models.CategoryData
import retrofit2.Call
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory


import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

//http://api.pennyup.club/api/getAllCategories
//http://api.pennyup.club/api/getCategoryById?id=626820b0d2c7aa7837855f07

const val BASE_URL = "http://api.pennyup.club/api/"

interface APIInterface {

    @GET("getAllCategories")
    suspend fun getAllCategories() : Response<APIResponse>

    @GET("getCategoryById")
    suspend fun getCategoryById(@Query("id") id:String) : Response<CategoryData>

}

object APIService{
    val retrofit  = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(APIInterface::class.java)

}