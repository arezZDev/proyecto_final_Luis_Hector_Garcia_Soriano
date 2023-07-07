package com.example.capitulo8.model

import android.util.Log
import androidx.compose.runtime.getValue // obtener y modificar el estado
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// En este archivo crearemos al view model que nos ayude a enviar los datos del web service a nuestra vista

data class DataState(
    // Estado inicial de la lista de perros
    val dogs:List<Dog> = listOf(
        Dog(
            message = "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bf/Bulldog_inglese.jpg/1200px-Bulldog_inglese.jpg"
        )
    )
)


// ViewModel para la aplicacion

class AppViewModel: ViewModel(){
    private val repository=DogRepository() // Iniciamos el repositorio de datos
    private val scope:CoroutineScope = viewModelScope // Se usa par ala creación de corrutinas
    var state by mutableStateOf<DataState>(DataState())
    private set

    // Este metodo modifica el estado de la lista de dogs, usando los datos del repositorio
    fun changeStateDogs(){
        try {
            scope.launch(Dispatchers.IO) {
                var perros=repository.getDogs()
                state = state.copy(
                    dogs=perros
                )
            }
        }catch (ex:Exception){
            Log.e("ERRORDOGS", ex.message.toString())
        }


    }
    fun changeStateDogsv2(breed: String) {
        //val breed = "affenpinscher" // Reemplaza con la raza que desees

        try {
            scope.launch(Dispatchers.IO) {
                var perros=repository.getDogsv2(breed)
                state = state.copy(
                    dogs=perros
                )
            }
        }catch (ex:Exception){
            Log.e("ERRORDOGS", ex.message.toString())
        }
    }




}