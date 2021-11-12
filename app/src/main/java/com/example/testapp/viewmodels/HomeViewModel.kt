package com.example.testapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.models.Employe
import com.example.testapp.repositories.EmployeesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel() {
    val employeeList = MutableLiveData<List<Employe>>()
    init {
        viewModelScope.launch {
            getEmployeeList()
        }
    }
    private suspend fun getEmployeeList() = withContext(Dispatchers.Default){
        val response = EmployeesRepository().getAllEmployees()
        if (response.isSuccessful){
            employeeList.postValue(response.body()!!.employees)
        }else{
            employeeList.value = emptyList()
        }
    }
}