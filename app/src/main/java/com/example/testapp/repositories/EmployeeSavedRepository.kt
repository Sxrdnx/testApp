package com.example.testapp.repositories

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import com.example.testapp.dao.EmployeeDao
import com.example.testapp.database.ApplicationDatabase
import com.example.testapp.models.Employee

class EmployeeSavedRepository(context: Context) {

    private val dbInstanceEmployee: EmployeeDao =  ApplicationDatabase.getDatabase(context).employeeDao

    fun getEmployeesSaved():LiveData<List<Employee>> = dbInstanceEmployee.getEmployees()

    suspend fun insertEmployee(employee: Employee){
        dbInstanceEmployee.insertEmployee(employee)
    }

    suspend fun deleteEmployee(employee: Employee){
        dbInstanceEmployee.removeEmployee(employee)
    }
        
}