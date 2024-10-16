package com.example.prueba1.data.model

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

data class PostModel(
    val id:Int,
    val title:String,
    val text:String,
    val image: Painter
)
