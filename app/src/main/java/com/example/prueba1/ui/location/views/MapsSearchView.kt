package com.example.prueba1.ui.location.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
@Composable
fun MapsSearchView(lat: Double, long: Double, address: String){
    val place = LatLng(lat, long) //Coordenadas de lat  y long
    val markerState = rememberMarkerState(position = place)
    val cameraPosition = CameraPosition.fromLatLngZoom(place, 10f)
    val cameraState = rememberCameraPositionState {
        position = cameraPosition
    }
    Box(modifier = Modifier.fillMaxSize()){
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraState
        ){
            MarkerInfoWindow( //Marcador en el lugar especificado
                state = markerState){
                CardMarker(address = address)
            }
        }
    }
}
@Composable
fun CardMarker(address: String) {
    Card(
        modifier = Modifier
            .padding(18.dp)
            .height(150.dp)
    )
    {
        Column(
            modifier = Modifier
                .padding(18.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Icon(imageVector = Icons.Default.LocationOn,
                contentDescription = "",
                modifier = Modifier.size(40.dp)
            )
            Text(text = address,
                modifier = Modifier.padding(all = 15.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}