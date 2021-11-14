package com.example.testapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.testapp.dao.EmployeeDao
import com.example.testapp.dao.StateLoginDao
import com.example.testapp.models.Employee
import com.example.testapp.models.StateLogin

@Database(entities =[StateLogin::class,Employee::class], version = 1,exportSchema = false )

abstract class ApplicationDatabase: RoomDatabase() {
    abstract  val stateLoginDao: StateLoginDao
    abstract val employeeDao : EmployeeDao
    companion object{
        private var INSTANCE: ApplicationDatabase? = null
        fun getDatabase(context: Context):ApplicationDatabase{
            synchronized(this){
                var instance = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ApplicationDatabase::class.java,
                        "application_db"
                    ).build()
                }
                return instance
            }
        }
    }
}