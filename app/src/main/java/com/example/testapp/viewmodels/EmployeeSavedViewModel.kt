package com.example.testapp.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.models.Employee
import com.example.testapp.repositories.EmployeeSavedRepository
import kotlinx.coroutines.launch

class EmployeeSavedViewModel(application: Application) : AndroidViewModel(application) {
    private val dbRepository: EmployeeSavedRepository = EmployeeSavedRepository(application)
    fun getSavedEmployees():LiveData<List<Employee>>{
        return dbRepository.getEmployeesSaved()
    }

    fun deleteEmployee(employee: Employee){
        viewModelScope.launch {
            dbRepository.deleteEmployee(employee)
        }
    }



}