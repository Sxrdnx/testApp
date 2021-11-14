package com.example.testapp.listeners

import com.example.testapp.models.Employee

interface EmployeeSavedListener {
    fun employeeOfLineClicked()
    fun removeEmployee(employee: Employee, position : Int)
}