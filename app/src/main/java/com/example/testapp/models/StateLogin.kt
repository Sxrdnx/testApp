package com.example.testapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "stateLogin")
data class StateLogin(
    @PrimaryKey
    @SerializedName("id") val id: Int=0,
    @SerializedName("state") var state: Boolean = false)
