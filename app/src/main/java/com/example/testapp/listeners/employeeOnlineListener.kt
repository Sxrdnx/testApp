package com.example.testapp.listeners

import com.example.testapp.models.Employee

interface EmployeeOnlineListener {
    fun onEmployeeClicked(employee: Employee)
    fun saveEmployee(employee: Employee)
}