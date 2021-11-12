package com.example.testapp.network

import com.example.testapp.models.Employe
import com.example.testapp.repositories.EmployeesResponse
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {
    @GET(".")
    suspend fun getListEmployees(): Response<EmployeesResponse>
}