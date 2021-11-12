package com.example.testapp.fragments

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.testapp.R
import com.example.testapp.activities.LoginActivity
import com.example.testapp.adapters.EmployeeAdapter
import com.example.testapp.databinding.HomeFragmentBinding
import com.example.testapp.models.Employee
import com.example.testapp.models.StateLogin
import com.example.testapp.util.goToActivity
import com.example.testapp.viewmodels.HomeViewModel
import com.example.testapp.viewmodels.LoginViewModel

class HomeFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel
    private lateinit var homeFragmentBinding: HomeFragmentBinding
    private lateinit var employeesAdapter : EmployeeAdapter
    private val employees = mutableListOf<Employee>()
    private lateinit var loginViewModel: LoginViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeFragmentBinding = DataBindingUtil.inflate(inflater,R.layout.home_fragment
        , container,false)
        doInitialisation()
        logOut()
        return homeFragmentBinding.root
    }

    private fun doInitialisation() {
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        employeesAdapter= EmployeeAdapter(employees)
        homeFragmentBinding.employeesRcv.adapter = employeesAdapter
        viewModel.employeeList.observe(viewLifecycleOwner,{
            employees.addAll(it)
            employeesAdapter.notifyDataSetChanged()
        })
    }

    private fun logOut() {
        homeFragmentBinding.imageLogout.setOnClickListener{
            loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
            loginViewModel.updateState(StateLogin(0,false))
            activity?.goToActivity<LoginActivity>{
                flags= Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
        }
    }
}