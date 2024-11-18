package com.example.prueba1.ui.network

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter


@Composable
fun NetworkImage(isHighQuality: Boolean) {
    val imageUrl = if (isHighQuality) {
        // URL de la imagen de alta calidad
        "https://st4.depositphotos.com/5906210/40964/i/450/depositphotos_409642058-stock-photo-smoky-sunset-santa-cruz-mountains.jpg"
    } else {
        // URL de la imagen de baja calidad
        "https://st4.depositphotos.com/5906210/40964/i/150/depositphotos_409642058-stock-photo-smoky-sunset-santa-cruz-mountains.jpg"
    }

    // Mostramos la imagen con la URL determinada
    Image(
        painter = rememberImagePainter(data = imageUrl),
        contentDescription = "Imagen de Red",
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    )
}

@Composable
fun ConnectionCard(title: String, content: String, networkSpeed: Int? = null) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Título de la tarjeta (generalmente el estado de la conexión)
            Text(
                text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )
            // Contenido principal de la tarjeta (como el estado de la conexión o el uso de datos)
            Text(
                text = content,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )
            // Si la velocidad de la red es proporcionada, la mostramos
            networkSpeed?.let {
                Text(
                    text = "Velocidad de Red: $it kbps",
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.secondary,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}