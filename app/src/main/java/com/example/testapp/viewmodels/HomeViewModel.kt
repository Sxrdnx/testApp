package com.example.testapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.models.Employee
import com.example.testapp.repositories.EmployeesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel() {
    private val _employeeList = MutableLiveData<List<Employee>>()
    val employeeList: LiveData<List<Employee>>
        get() =_employeeList
    init {
        viewModelScope.launch {
            getEmployeeList()
        }
    }
    private suspend fun getEmployeeList() = withContext(Dispatchers.Default){
        val response = EmployeesRepository().getAllEmployees()
        if (response.isSuccessful){
            _employeeList.postValue(response.body()!!.employees)
        }else{
            _employeeList.value = emptyList()
        }
    }
}