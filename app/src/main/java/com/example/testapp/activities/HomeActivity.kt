package com.example.testapp.activities


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.testapp.R


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //val nav = findNavController(R.id.fragmentContainerView)
        setContentView(R.layout.activity_home)
    }

}