package com.example.testapp.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.testapp.R
import com.example.testapp.databinding.EmployDetailsFragmentBinding
import com.example.testapp.viewmodels.EmployDetailsViewModel

class EmployDetails : Fragment() {

    private lateinit var viewModel: EmployDetailsViewModel
    private lateinit var detailsBinding: EmployDetailsFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(EmployDetailsViewModel::class.java)
        detailsBinding = DataBindingUtil.inflate(inflater,R.layout.employ_details_fragment,
        container,false)
        val argument = EmployDetailsArgs.fromBundle(requireArguments())
        doInitialisation(argument)
        return detailsBinding.root
    }

    private fun doInitialisation(arg: EmployDetailsArgs) {
        detailsBinding.employee = arg.employee
        detailsBinding.calificacion = arg.employee.calificacion.toString()
    }

}