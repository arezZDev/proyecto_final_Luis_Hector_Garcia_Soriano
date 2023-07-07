package com.example.capitulo8.model

import androidx.annotation.WorkerThread

// Esta clase sera usada en el view model para obtener los datos del web service

class DogRepository{
    private val dogService= RetrofitInstance.service

    //@WorkerThread

    suspend fun getDogs(): List<Dog> {
        val response = dogService.getDogs()
        val dog = Dog(response.message)
        return listOf(dog)
    }

    suspend fun getDogsv2(breed: String): List<Dog> {
        val response = dogService.getRandomDogByBreed(breed)
        val dog = Dog(response.message)
        return listOf(dog)
    }

}