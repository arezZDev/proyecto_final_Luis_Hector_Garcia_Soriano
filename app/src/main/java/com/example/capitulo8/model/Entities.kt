package com.example.capitulo8.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


// Creamos la clase que ayudara a moshi a convertir el json a un objeto

@JsonClass(generateAdapter = true)
data class Dog(
    val message:String
)