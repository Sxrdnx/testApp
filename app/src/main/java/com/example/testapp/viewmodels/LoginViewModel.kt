package com.example.testapp.viewmodels

import android.util.Patterns
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel(){

    fun isValidEmail(email: String):Boolean{
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    fun isValidPassword(password: String): Boolean{
        return password.length >= 4
    }
}