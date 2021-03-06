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

class EmployDetailsFragment : Fragment() {
    private lateinit var detailsBinding: EmployDetailsFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailsBinding = DataBindingUtil.inflate(inflater,R.layout.employ_details_fragment,
        container,false)
        val argument = EmployDetailsFragmentArgs.fromBundle(requireArguments())
        doInitialisation(argument)
        return detailsBinding.root
    }

    private fun doInitialisation(arg: EmployDetailsFragmentArgs) {
        detailsBinding.employee = arg.employee
        detailsBinding.calificacion = arg.employee.calificacion.toString()
    }

}