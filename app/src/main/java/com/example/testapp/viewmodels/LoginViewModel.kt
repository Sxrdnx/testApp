package com.example.testapp.viewmodels

import android.app.Application
import android.util.Patterns
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.models.StateLogin
import com.example.testapp.repositories.StateLoginRepository
import kotlinx.coroutines.launch

class LoginViewModel( application: Application): AndroidViewModel(application){
    private val dbRepository = StateLoginRepository(application)
    val stateLogin = dbRepository.getState()

    fun updateState(state: StateLogin){
        viewModelScope.launch {
            dbRepository.insertOrUpdateState(state)
        }
    }
    fun isValidEmail(email: String):Boolean{
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    fun isValidPassword(password: String): Boolean{
        return password.length >= 4
    }
}