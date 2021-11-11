package com.example.testapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.testapp.activities.Home
import com.example.testapp.activities.Login
import com.example.testapp.util.goToActivity
import com.example.testapp.viewmodels.LoginViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var loginViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        loginViewModel.stateLogin.observe(this,{
                if(it?.state == true && it != null){
                    goToActivity<Home>(){
                        flags= Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }
                }else{
                    goToActivity<Login>(){
                        flags= Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }
                }
                finish()
        })
    }



}