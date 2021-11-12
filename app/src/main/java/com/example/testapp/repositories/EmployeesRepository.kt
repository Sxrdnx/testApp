package com.example.testapp.repositories

import com.example.testapp.network.ApiClient
import com.example.testapp.network.ApiService

class EmployeesRepository {
    private val apiServ: ApiService = ApiClient.getRetrofit().create(ApiService::class.java)

    suspend fun getAllEmployees() = apiServ.getListEmployees()
}