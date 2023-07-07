package com.example.capitulo8.model

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

// url base del web service

//val URL_BASE = "http://www.patitonet.com"
val URL_BASE = "https://dog.ceo/api/"
// https://dog.ceo/api/breed/variable/images/random

// Interface usada para mapear los metodos del servicio web

// GET, POST, PUT, DELETE, PATCH

@JsonClass(generateAdapter = true)
interface DogService {
    @GET("breeds/image/random")
    suspend fun getDogs(): Dog

    @GET("breed/{breed}/images/random")
    suspend fun getRandomDogByBreed(@Path("breed") breed: String): Dog
}


// creamos la clase para conectarlos al servicio web, usando retrofit
object RetrofitInstance{
    // declaramos a moshi (conversor json -> class)
    private val moshi=Moshi
        .Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    // crear conexion con retrofit
    private val retrofit:Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }
    // Servicio Externo de retrofit
    val service:DogService by lazy {
        retrofit.create(DogService::class.java)
    }

    val anotherService: DogService by lazy {
        retrofit.newBuilder()
            .baseUrl(URL_BASE) // Reemplaza con la nueva URL base del nuevo path
            .build()
            .create(DogService::class.java)
    }
}