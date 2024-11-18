package com.example.prueba1.ui.camera

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.navigation.NavController

@Composable
fun CameraScreen(context: Context,navController: NavController) {
    // Lista donde se guardan las URIs de las imágenes que se van agregando.
    val imageUris = remember { mutableStateListOf<Uri>() }
    // Lanzador de permisos. Si no se concede, se corta la función sin hacer nada más.
    val permissionLauncher = rememberPermissionLauncher(context) { granted ->
        if (!granted) return@rememberPermissionLauncher
    }
    // Lanzador de cámara. Cuando se toma una foto, la URI resultante se guarda en la lista de imágenes.
    val cameraLauncher = rememberCameraLauncher(context) { uri ->
        uri?.let { imageUris.add(it) }
    }
    // Lanzador de galería. Cuando se selecciona una imagen, la URI resultante se guarda en la lista.
    val galleryLauncher = rememberGalleryLauncher { uri ->
        uri?.let { imageUris.add(it) }
    }
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(imageUris) { uri ->
                    Box(modifier = Modifier.padding(bottom = 10.dp)) {
                        // Obtener el bitmap de la URI utilizando BitmapFactory
                        val bitmap = BitmapFactory.decodeStream(context.contentResolver.openInputStream(uri))
                        bitmap?.let {
                            Image(
                                bitmap = it.asImageBitmap(),
                                contentDescription = "Imagen tomada o seleccionada",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                            )
                        }
                        IconButton(
                            onClick = { imageUris.remove(uri) },
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .size(36.dp)
                                .padding(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Eliminar imagen",
                                tint = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                FloatingActionButton(
                    onClick = {
                        navController.popBackStack()
                    },
                    containerColor = MaterialTheme.colorScheme.primary,
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowLeft,
                        contentDescription = "Go Back"
                    )
                }
                FloatingActionButton(
                    onClick = {
                        requestPermissions(context, permissionLauncher) {
                            cameraLauncher.launch(null)
                        }
                    },
                    containerColor = MaterialTheme.colorScheme.primary,
                ) {
                    Icon(
                        imageVector = Icons.Default.Face,
                        contentDescription = "Abrir cámara"
                    )
                }
                FloatingActionButton(
                    onClick = {
                        requestPermissions(context, permissionLauncher) {
                            galleryLauncher.launch("image/*")
                        }
                    },
                    containerColor = MaterialTheme.colorScheme.primary,
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Importar Imagen"
                    )
                }
                FloatingActionButton(
                    onClick = { imageUris.clear() },
                    containerColor = MaterialTheme.colorScheme.error,
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Eliminar todas las imágenes"
                    )
                }
            }
        }
    }
}