package com.example.testapp.activities


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.testapp.R
import com.example.testapp.util.ConnectionLiveData
import com.example.testapp.util.toast


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val nav = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val nv= nav.navController
        val connection = ConnectionLiveData(this)
        connection.observe(this,{ isConnected ->
            if (isConnected){
                nv.popBackStack()
                nv.navigate(R.id.homeFragment)
            }else{
                toast("Sin Coneccion!")
                nv.popBackStack()
                nv.navigate(R.id.employeeSavedFragment)
            }
        })
    }

}