package com.example.testapp.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.testapp.models.Employee
import com.example.testapp.models.StateLogin

@Dao
interface EmployeeDao {
    @Query("SELECT * FROM employees")
    fun getEmployees(): LiveData<List<Employee>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmployee(employee: Employee)

    @Delete
    suspend fun removeEmployee(employee: Employee)


}