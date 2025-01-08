package com.example.comunicaciones

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.compose.ComunicacionesTheme
import com.example.comunicaciones.presentation.navigation.NavigationRoot

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComunicacionesTheme {
                val navController = rememberNavController()
                NavigationRoot(navController)
            }
        }
    }
}

