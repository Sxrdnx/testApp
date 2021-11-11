package com.example.testapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.testapp.R
import com.example.testapp.models.StateLogin
import com.example.testapp.util.goToActivity
import com.example.testapp.viewmodels.LoginViewModel

class Home : AppCompatActivity() {
    private lateinit var loginViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)
        logOut()
    }
    private fun logOut(){
        val txtT = findViewById<TextView>(R.id.textTemp)
        txtT.setOnClickListener {
            loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
            loginViewModel.updateState(StateLogin(0,false))
            goToActivity<Login>{
                flags= Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
        }
    }
}