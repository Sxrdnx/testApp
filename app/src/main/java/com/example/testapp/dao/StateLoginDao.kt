package com.example.testapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testapp.models.StateLogin

@Dao
interface StateLoginDao {
    @Query("SELECT * FROM stateLogin")
    fun getStateLogin(): LiveData<StateLogin>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertState(state: StateLogin)
}