package com.example.testapp.network

import com.example.testapp.responses.EmployeesResponse
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {
    @GET(".")
    suspend fun getListEmployees(): Response<EmployeesResponse>
}