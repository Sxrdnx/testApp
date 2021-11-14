package com.example.testapp.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.testapp.dao.StateLoginDao
import com.example.testapp.database.ApplicationDatabase
import com.example.testapp.models.StateLogin

class StateLoginRepository(application: Application) {
    private val dbInstanceStateLoginDao: StateLoginDao = ApplicationDatabase.getDatabase(application).stateLoginDao
    fun getState(): LiveData<StateLogin> = dbInstanceStateLoginDao.getStateLogin()

    suspend fun insertOrUpdateState(stateLogin: StateLogin){
        dbInstanceStateLoginDao.insertState(stateLogin)
    }
}