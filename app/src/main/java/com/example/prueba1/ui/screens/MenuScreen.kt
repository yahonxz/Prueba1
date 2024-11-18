package com.example.prueba1.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun MenuScreen(navController: NavController){ //Pantalla que se invoca
    Column(
        modifier = Modifier.fillMaxSize()
    ){
        Text(text="This is the Menu Screen")
        Button(onClick = { navController.navigate("home")}) {
            Icon(Icons.Filled.Home,"")
        }
        Button(onClick = { navController.navigate("components")}) {
            Text("Components")
        }

        Button(onClick = { navController.navigate("login")}) {
            Text("Login")
        }
        Button(onClick = { navController.navigate("Camera")}) {
            Text("Camera")
        }
        Button(onClick = { navController.navigate("internet")}) {
            Text("Internet")
        }
        Button(onClick = { navController.navigate("contacts")}) {
            Text("Contacts")
        }
        Button(onClick = { navController.navigate("biometrics")}) {
            Text("Biometrics")
        }
        Button(onClick = { navController.navigate("homeMaps")}) {
            Text("Maps")
        }
    }
}