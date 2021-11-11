package com.example.testapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.testapp.R
import com.example.testapp.databinding.ActivityLoginBinding
import com.example.testapp.util.toast
import com.example.testapp.util.validate
import com.example.testapp.viewmodels.LoginViewModel
import com.squareup.picasso.Picasso

class Login : AppCompatActivity() {
    private lateinit var loginBinding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding= DataBindingUtil.setContentView(this,R.layout.activity_login)
        doInitialization()
        checkValidationText()
        login()
        setContentView(loginBinding.root)
    }

    private fun login() {
        loginBinding.singInLogin.setOnClickListener{
            val email = loginBinding.emailLoginEditText.text.toString()
            val password=loginBinding.passwordLoginEditText.text.toString()
            if(loginViewModel.isValidEmail(email) && loginViewModel.isValidPassword(password)) {
                toast("si pasa")
            } else {
                toast("Asegúrese de que todos los datos sean correctos.")
            }
        }
    }

    private fun doInitialization(){
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        Picasso.get()
            .load(R.drawable.holderimage)
            .into(loginBinding.holderImage)
    }

    private fun checkValidationText() {
        loginBinding.emailLoginEditText.validate {
            loginBinding.emailLoginEditText.error = if (loginViewModel.isValidEmail(it)) null else "Correo invalido"
        }
        loginBinding.passwordLoginEditText.validate {
            loginBinding.passwordLoginEditText.error = if(loginViewModel.isValidPassword(it)) null else "La contraseña debe tener tamaño minimo de 4 caracteres"
        }
    }
}