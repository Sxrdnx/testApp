package com.example.testapp.fragments

import android.annotation.SuppressLint
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import com.example.testapp.R
import com.example.testapp.activities.LoginActivity
import com.example.testapp.adapters.EmployeeAdapter
import com.example.testapp.databinding.HomeFragmentBinding
import com.example.testapp.listeners.EmployeeOnlineListener
import com.example.testapp.models.Employee
import com.example.testapp.models.StateLogin
import com.example.testapp.responses.EmployeesResponse
import com.example.testapp.util.ConnectionLiveData
import com.example.testapp.util.goToActivity
import com.example.testapp.util.toast
import com.example.testapp.viewmodels.HomeViewModel
import com.example.testapp.viewmodels.LoginViewModel
import retrofit2.Response

class HomeFragment : Fragment(),EmployeeOnlineListener {
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
        loading()
        val connection = ConnectionLiveData(requireActivity())
        connection.observe(viewLifecycleOwner,{ isConnected ->
            employees.clear()
            if (isConnected){
                getWithConnection()
            }else{
                employeesAdapter.notifyDataSetChanged()
                requireActivity().toast("sin internet")
            }
        })
        logOut()
        navDetail()
        return homeFragmentBinding.root
    }

    private fun loading() {
        viewModel.isLoading.observe(viewLifecycleOwner,{
            if (it){
                homeFragmentBinding.progressBar.visibility=View.VISIBLE
            }else{
                homeFragmentBinding.progressBar.visibility=View.GONE
            }
        })
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun getWithConnection(){
        viewModel.employeeList.observe(viewLifecycleOwner,{
            if(it.isNotEmpty() ){
                employees.addAll(it)
                employeesAdapter.notifyDataSetChanged()
            }else{
                requireActivity().toast("Sin elementos")
            }
        })
    }

    private fun doInitialisation() {
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        employeesAdapter= EmployeeAdapter(employees,this)
        homeFragmentBinding.employeesRcv.adapter = employeesAdapter

    }

    private fun logOut() {
        homeFragmentBinding.imageLogout.setOnClickListener{
            loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
            loginViewModel.updateState(StateLogin(0,false))
            requireActivity().goToActivity<LoginActivity>{
                flags= Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
        }
    }
    private fun navDetail(){
        viewModel.navigateToDetail.observe(viewLifecycleOwner,{employee->
            employee?.let {
                this.findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToEmployDetails(employee))
                viewModel.doneNavigation()
                employees.clear()
            }
        })
    }
    override fun onEmployeeClicked(employee: Employee) {
        viewModel.makeNavigation(employee)
    }
}