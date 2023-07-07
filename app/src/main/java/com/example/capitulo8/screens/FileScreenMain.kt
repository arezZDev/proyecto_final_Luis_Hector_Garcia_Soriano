package com.example.capitulo8.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import coil.compose.AsyncImage
import com.example.capitulo8.model.AppViewModel
import com.example.capitulo8.model.Dog


// Luis Héctor García Soriano
// Hice lo que pude :,v
@Composable
fun ScreenMain(appModel:AppViewModel){
    val options = listOf("affenpinscher", "african", "airedale","dalmatian", "entlebucher")
    var state = appModel.state
    Column(modifier = Modifier.fillMaxSize()) {
        // Generamos espacio
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)){
            items(state.dogs){
                dog->
                // Generamos espacio
                TarjetaPerro(dog = dog)

            }

        }
        // Generamos espacio
        Spacer(modifier = Modifier.height(5.dp))
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                appModel.changeStateDogs()
            },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White) // Cambia el color de fondo del botón

            ) {
                Text(text = "Perros Aleatorios")
            }
        }

        // Aquí inicia el segundo punto
        Spacer(modifier = Modifier.height(10.dp))
        val expandedState = remember { mutableStateOf(false) }
        val selectedOption = remember { mutableStateOf(options[0]) }
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                expandedState.value = !expandedState.value

            },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black) // Cambia el color de fondo del botón
            ) {
                Text(
                    text = "Selecciona una raza:",
                    fontSize = 15.sp,
                    color = Color.White
                )
            }
        }
        if (expandedState.value) {
            DropdownMenu(
                modifier = Modifier.width(200.dp),
                expanded = true, // Estado para controlar la visibilidad del menú
                onDismissRequest = { expandedState.value = false },
                properties = PopupProperties(focusable = false) // Evita problemas de enfoque en el contenido
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        onClick = {
                            selectedOption.value = option
                            expandedState.value = false
                        }
                    ) {
                        Text(text = option)
                    }
                }
            }
        }
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                "Opción seleccionada: ${selectedOption.value}",
                fontSize = 20.sp,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                appModel.changeStateDogsv2(selectedOption.value)
            },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White) // Cambia el color de fondo del botón
            ) {
                Text(
                    text = "Generar x Raza",
                    color = Color.Black
                )
            }
        }
    }
}


@Composable
fun TarjetaPerro(dog: Dog){
    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center

    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center ) {
            AsyncImage(
                model = dog.message ,
                contentDescription = "perro",
                modifier = Modifier
            )
            // Generamos espacio
            Spacer(modifier = Modifier.width(30.dp))
        }

    }

}


@Preview
@Composable
fun PreviewMain(){
    ScreenMain(appModel = AppViewModel())
}