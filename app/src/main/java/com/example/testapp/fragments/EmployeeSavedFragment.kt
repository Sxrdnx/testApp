package com.example.testapp.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.testapp.R
import com.example.testapp.adapters.EmployeeSavedAdapter
import com.example.testapp.databinding.EmployeeSavedFragmentBinding
import com.example.testapp.listeners.EmployeeSavedListener
import com.example.testapp.models.Employee
import com.example.testapp.util.DataHolder
import com.example.testapp.util.toast
import com.example.testapp.viewmodels.EmployeeSavedViewModel

class EmployeeSavedFragment : Fragment(),EmployeeSavedListener {
    private lateinit var viewModel: EmployeeSavedViewModel
    private lateinit var employeeSavedBinding: EmployeeSavedFragmentBinding
    private lateinit var employeeSavedAdapter: EmployeeSavedAdapter
    private val employeesSaved = mutableListOf<Employee>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        employeeSavedBinding = DataBindingUtil.inflate(inflater,R.layout.employee_saved_fragment,
        container,false)
        doInitialisation()
        return employeeSavedBinding.root
    }

    private fun doInitialisation() {
        employeeSavedAdapter = EmployeeSavedAdapter(employeesSaved,this)
        viewModel = ViewModelProvider(this)[EmployeeSavedViewModel::class.java]
        loadEmployeesSaved()
    }
    private fun loadEmployeesSaved(){
        viewModel.getSavedEmployees().observe(viewLifecycleOwner,{ listEmpl->
            if(employeesSaved.size > 0)
                employeesSaved.clear()
            employeesSaved.addAll(listEmpl)
            employeeSavedBinding.Rcv.adapter=employeeSavedAdapter
        })
    }

    override fun employeeOfLineClicked() {
        requireActivity().toast("Sin Conexión")
    }

    override fun removeEmployee(employee: Employee, position: Int) {
        viewModel.deleteEmployee(employee)
        employeeSavedAdapter.notifyItemRemoved(position)
        employeeSavedAdapter.notifyItemRangeChanged(position,employeeSavedAdapter.itemCount)
    }

    override fun onResume() {
        super.onResume()
        if (DataHolder.IS_UPDATE){
            loadEmployeesSaved()
            DataHolder.IS_UPDATE=false
        }
    }


}