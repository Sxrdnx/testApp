package com.example.testapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName="employees")
data class Employee(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id") val id: Int = 0,
    @SerializedName("firstName") val nombre: String = "",
    @SerializedName("lastName") val apellido: String = "",
    @SerializedName("image") val imagen: String="",
    @SerializedName("description") val descripcion: String="",
    @SerializedName("rating") val calificacion: Float = 0.0f
    ): Serializable

