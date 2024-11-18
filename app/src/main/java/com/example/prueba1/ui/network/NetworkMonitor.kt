package com.example.prueba1.ui.network

import android.Manifest
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.TrafficStats
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.prueba1.MainActivity
import kotlinx.coroutines.delay

// Esta clase monitorea el estado de la red (WiFi o Datos Móviles) y muestra información sobre el consumo de datos.
class NetworkMonitor(
    private val wifiManager: WifiManager,
    private val connectivityManager: ConnectivityManager,
    private val activity: MainActivity
) {

    // Función privada que obtiene el estado de la conexión (WiFi o Datos Móviles)
    private fun getConnectionStatus(): String {
        val networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        val isWifiConnected = wifiManager.isWifiEnabled && networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true
        val isMobileConnected = networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == true

        // Se determina el estado de la conexión (WiFi, Datos Móviles o Sin Conexión)
        return when {
            isWifiConnected -> {
                // Si la conexión es WiFi, verificamos si tenemos permisos para obtener el SSID
                if (ContextCompat.checkSelfPermission(
                        activity,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    val wifiInfo: WifiInfo? = wifiManager.connectionInfo
                    val ssid = wifiInfo?.ssid?.replace("\"", "") ?: "Desconocido"
                    "Conectado a WiFi: $ssid"  // Retorna el nombre de la red WiFi
                } else {
                    "Conectado a WiFi (Nombre de red no disponible)"
                }
            }
            isMobileConnected -> "Conectado a Datos Móviles"  // Si está conectado a datos móviles
            else -> "Sin conexión a Internet"
        }
    }

    // Función que devuelve si el dispositivo está usando datos móviles
    fun isUsingMobileData(): Boolean {
        return connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            ?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == true
        // Verifica si la red activa es de datos móviles
    }

    // Composable que muestra la pantalla de monitoreo de la red
    @Composable
    fun NetworkMonitorScreen(navController: NavController) {
        // Variables para almacenar el estado de la conexión y el uso de datos
        var connectionStatus by remember { mutableStateOf("Sin conexión a Internet") }
        var mobileDataUsage by remember { mutableStateOf(0L) }
        var wifiDataUsage by remember { mutableStateOf(0L) }
        var networkSpeed by remember { mutableStateOf(0) }
        var isHighQualityImage by remember { mutableStateOf(false) }

        // LaunchedEffect para realizar tareas asíncronas (monitorear la red)
        LaunchedEffect(Unit) {
            // Solicitamos los permisos necesarios para acceder a la información de WiFi
            activity.requestPermissionsIfNeeded()

            // Variables para almacenar el consumo de datos anteriores
            var lastMobileBytes = TrafficStats.getMobileRxBytes() + TrafficStats.getMobileTxBytes()
            var lastWifiBytes = TrafficStats.getTotalRxBytes() - lastMobileBytes

            while (true) {
                // Actualizamos el estado de la conexión
                connectionStatus = getConnectionStatus()
                val isMobileConnected = isUsingMobileData()
                isHighQualityImage = !isMobileConnected
                // Si estamos usando datos móviles, mostramos imagen de baja calidad

                // Obtiene el uso actual de datos móviles y WiFi
                val currentMobileBytes = TrafficStats.getMobileRxBytes() + TrafficStats.getMobileTxBytes()
                val currentWifiBytes = TrafficStats.getTotalRxBytes() - currentMobileBytes

                // Calculamos el consumo de datos móviles y WiFi
                val mobileDataUsed = currentMobileBytes - lastMobileBytes
                val wifiDataUsed = currentWifiBytes - lastWifiBytes

                // Si estamos usando datos móviles, calculamos la velocidad de la red y actualizamos el consumo de datos
                if (isMobileConnected && mobileDataUsed > 0) {
                    networkSpeed = ((mobileDataUsed * 8) / 1024).toInt()  // Velocidad en kbps
                    mobileDataUsage += mobileDataUsed
                    lastMobileBytes = currentMobileBytes
                }
                // Si estamos usando WiFi, calculamos la velocidad de la red y actualizamos el consumo de datos
                else if (!isMobileConnected && wifiDataUsed > 0) {
                    networkSpeed = ((wifiDataUsed * 8) / 1024).toInt()  // Velocidad en kbps
                    wifiDataUsage += wifiDataUsed
                    lastWifiBytes = currentWifiBytes
                } else {
                    networkSpeed = 0  // Si no hay datos transferidos, la velocidad es 0
                }

                delay(500L)  // Actualizamos los datos cada 500ms
            }
        }

        // Diseño de la pantalla de monitoreo de la red
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Row(){

                Button(onClick = {navController.popBackStack()}){
                    Icon(Icons.Filled.KeyboardArrowLeft,"Go Back")
                }

                // Si tenemos conexión, mostramos la imagen de acuerdo a la calidad de la red
                if (connectionStatus != "Sin conexión a Internet") {
                    NetworkImage(isHighQualityImage)
                } else {
                    // Si no hay conexión, mostramos un mensaje
                    Text(
                        "Sin conexión para cargar la imagen",
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }


            Spacer(modifier = Modifier.height(16.dp))

            // Mostramos tarjetas con información sobre el estado de la conexión, uso de datos móviles y WiFi
            ConnectionCard("Estado de la Conexión", connectionStatus, networkSpeed)
            Spacer(modifier = Modifier.height(16.dp))
            ConnectionCard("Consumo de Datos Móviles", "${mobileDataUsage / (1024 * 1024)} MB")
            Spacer(modifier = Modifier.height(16.dp))
            ConnectionCard("Consumo de Datos WiFi", "${wifiDataUsage / (1024 * 1024)} MB")


        }
    }
}