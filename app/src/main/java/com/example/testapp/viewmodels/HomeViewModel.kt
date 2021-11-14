package com.example.testapp.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.testapp.models.Employee
import com.example.testapp.repositories.EmployeeSavedRepository
import com.example.testapp.repositories.EmployeesRepository
import com.example.testapp.responses.EmployeesResponse
import kotlinx.coroutines.*
import retrofit2.Response

class HomeViewModel (application: Application): AndroidViewModel(application    ) {
    private val dbRepository = EmployeeSavedRepository(application)

    private val _employeeList = MutableLiveData<List<Employee>>()
    val employeeList: LiveData<List<Employee>>
        get() =_employeeList

    private val _navigateToDetail = MutableLiveData<Employee?>()
    val navigateToDetail: LiveData<Employee?>
        get() = _navigateToDetail

    val isLoading = MutableLiveData(true)

    private val errorMessage = MutableLiveData<String>()
    private var job: Job? = null

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled --->: ${throwable.localizedMessage}")
    }

    init {
        getAllEmployees()
    }
    private fun getAllEmployees() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response =EmployeesRepository().getAllEmployees()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _employeeList.postValue(response.body()?.employees?: emptyList())
                    isLoading.postValue(false)
                } else {
                    onError("Error --->: ${response.message()} ")
                }
            }
        }
    }

    fun saveEmployee(employee: Employee){
        viewModelScope.launch {
            dbRepository.insertEmployee(employee)
        }
    }
    fun doneNavigation(){
        _navigateToDetail.value= null
    }
    fun makeNavigation(employee: Employee){
        _navigateToDetail.value = employee
    }
    private fun onError(message: String) {
        errorMessage.postValue(message)
        isLoading.postValue(false)
    }
    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}