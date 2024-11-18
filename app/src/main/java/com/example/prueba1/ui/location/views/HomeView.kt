package com.example.prueba1.ui.location.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.prueba1.ui.location.viewModel.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(navController: NavController, searchVM: SearchViewModel){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Buscar lugar")
                }
            )
        }
    ) {pad ->
        Column(modifier = Modifier
            .padding(pad)
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {navController.popBackStack()}){
                Icon(Icons.Filled.KeyboardArrowLeft,"Go Back")
            }
            var search by remember { mutableStateOf("") }
            OutlinedTextField(
                value = search,
                onValueChange = {search = it},
                label = { Text(text = "Buscar")}
            )
            OutlinedButton(onClick = { searchVM.getLocation(search) }) {
                Text(text = "Buscar")
            }
            if (searchVM.show){
                Text(text = "Latitude: ${searchVM.lat}")
                Text(text = "Longitude: ${searchVM.long}")
                Text(text = "Dirección: ${searchVM.address}")
                OutlinedButton(onClick = { navController.navigate("MapsSearchView/${searchVM.lat}/${searchVM.long}/${searchVM.address}") }) {
                    Text(text = "Enviar")
                }
            }
        }
    }
}