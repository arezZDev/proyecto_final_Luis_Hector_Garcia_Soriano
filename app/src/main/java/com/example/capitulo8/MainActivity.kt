package com.example.capitulo8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.capitulo8.screens.ScreenMain
import com.example.capitulo8.ui.theme.Capitulo8Theme
import com.example.capitulo8.model.AppViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Registrar e iniciar el viewModel
        val appModel by viewModels<AppViewModel> ()

        setContent {
            ScreenMain(appModel)
        }
    }
}