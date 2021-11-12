package com.example.testapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object{
        fun getRetrofit():Retrofit{
             return Retrofit.Builder()
                .baseUrl("https://demo3535907.mockable.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}