package com.example.prueba1.ui.location.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prueba1.ui.location.model.GoogleGeoResult
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL
class SearchViewModel: ViewModel() {
    var lat by mutableDoubleStateOf(0.0)
        private set // significa que solo puede ser modificada dentro de esta clase
    var long by mutableDoubleStateOf(0.0)
        private set
    var address by mutableStateOf("")
        private set
    var show by mutableStateOf(false)
        private set
    fun getLocation(search: String){
        viewModelScope.launch {
            val apikey = "AIzaSyA4uRUwSHcbfQRuEn2By0U0-oFPy73aoi4"
            val url = "https://maps.googleapis.com/maps/api/geocode/json?address=$search&key=$apikey"
            val response = withContext(Dispatchers.IO){ //Solicitud HTTP de la API en un hilo de entrada/salida (IO)
                URL(url).readText() // crea una conexión HTTP con la URL especificada y lee la respuesta como una cadena de texto (JSON).
            }
            //Convierte la respuesta JSON en un objeto GoogleGeoResult utilizando la biblioteca Gson.
            val results = Gson().fromJson(response, GoogleGeoResult::class.java)
            if (results.results.isNotEmpty()){
                show = true //para indicar que hay datos disponibles
                lat = results.results[0].geometry.location.lat
                long = results.results[0].geometry.location.lng
                address = results.results[0].formatted_address
            }else{
                Log.d("Fail", "No funciona asi")
            }
        }
    }
}