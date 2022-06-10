package com.example.stockscrypto

import android.util.Log
import com.example.models.APIResponse
import com.example.models.Category
import com.example.models.Data
import java.lang.Exception

class Repository {
    lateinit var  categories: List<Category>


    suspend fun getAllCategories(): List<Category>? {
        val response = APIService.api.getAllCategories()
        try {
            if(response.isSuccessful){
                Log.d("Repository", response.body()?.message.toString())
                return response.body()?.data
            }
        }catch (e:Exception){
            Log.d("Repository","error in fetching categories")
        }

        return listOf()


    }

    suspend fun getCategoryById(id: String): Data? {
        val response = APIService.api.getCategoryById(id)
        if(response.isSuccessful){
            Log.d("Repository2", response.body()?.message.toString())
            return response.body()?.data
        }else{
            Log.d("Repository2","failed to get category data")
        }
        return null
    }
}