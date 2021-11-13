package com.example.testapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.databinding.ItemContainerEmployeeBinding
import com.example.testapp.listeners.EmployeeOnlineListener
import com.example.testapp.models.Employee

class EmployeeAdapter(private val employees: List<Employee>,
                      private val employeeListener: EmployeeOnlineListener) : RecyclerView.Adapter<EmployeeAdapter.ViewHolder>(){
    private lateinit var layoutInflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemContainerEmployeeBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.item_container_employee,
            parent,
            false
        )
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int)=holder.bind(employees[position])

    override fun getItemCount() = employees.size

    inner class ViewHolder(private val itemContainerEmployeeBinding:ItemContainerEmployeeBinding)
        : RecyclerView.ViewHolder(itemContainerEmployeeBinding.root){
        fun bind (employee: Employee){
            itemContainerEmployeeBinding.employee = employee
            itemContainerEmployeeBinding.imageSave.visibility = View.VISIBLE
            itemContainerEmployeeBinding.executePendingBindings()
            itemContainerEmployeeBinding.root.setOnClickListener {
                employeeListener.onEmployeeClicked(employee)
            }
        }
    }
}