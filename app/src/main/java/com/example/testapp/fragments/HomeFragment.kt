package com.example.testapp.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.testapp.R
import com.example.testapp.databinding.HomeFragmentBinding
import com.example.testapp.viewmodels.HomeViewModel

class HomeFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel
    private lateinit var homeFragmentBinding: HomeFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeFragmentBinding = DataBindingUtil.inflate(inflater,R.layout.home_fragment
        , container,false)
        doInitialisation()
        return homeFragmentBinding.root
    }

    private fun doInitialisation() {
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        homeFragmentBinding.pruebaButtom.setOnClickListener {
            viewModel.employeeList.observe(viewLifecycleOwner,{

            })
        }
    }


}