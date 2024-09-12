package com.example.prueba1.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController


@Composable
fun MenuScreen(navController: NavController){
    Column {
        Text(text = "This is new menu screen")
        Button(onClick = {navController.navigate("home")}) {
            
        }
    }
}