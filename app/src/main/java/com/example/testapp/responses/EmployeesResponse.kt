package com.example.testapp.responses

import com.example.testapp.models.Employee
import com.google.gson.annotations.SerializedName

data class EmployeesResponse(
    @SerializedName("employees")
    val employees: List<Employee>)
