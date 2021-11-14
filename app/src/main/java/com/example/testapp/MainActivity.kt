package com.example.testapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.testapp.activities.HomeActivity
import com.example.testapp.activities.LoginActivity
import com.example.testapp.util.goToActivity
import com.example.testapp.viewmodels.LoginViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var loginViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.themeSplash)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        loginViewModel.stateLogin.observe(this,{
                if(it?.state == true ){
                    goToActivity<HomeActivity>(){
                        flags= Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }
                }else{
                    goToActivity<LoginActivity>(){
                        flags= Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }
                }
                finish()
        })
    }
}