package com.example.testapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.models.Employee
import com.example.testapp.repositories.EmployeesRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel() {
    private val _employeeList = MutableLiveData<List<Employee>>()
    val employeeList: LiveData<List<Employee>>
        get() =_employeeList
    private val errorMessage = MutableLiveData<String>()
    private val _navigateToDetail = MutableLiveData<Employee>()
    val navigateToDetail: LiveData<Employee>
        get() = _navigateToDetail

    val isLoading = MutableLiveData(true)

    init {
        viewModelScope.launch {
            getEmployeeList()
        }
    }
    fun doneNavigation(){
        _navigateToDetail.value= null
    }
    fun makeNavigation(employee: Employee){
        _navigateToDetail.value = employee
    }

    private suspend fun getEmployeeList() = withContext(Dispatchers.Default){
        val response = EmployeesRepository().getAllEmployees()
        if (response.isSuccessful){
            _employeeList.postValue(response.body()!!.employees)
            isLoading.postValue(false)
        }else{
            onError("Error : ${response.message()} ")
            _employeeList.postValue(emptyList())
        }
    }
    private fun onError(message: String) {
        errorMessage.value = message
        isLoading.value = false
    }
}