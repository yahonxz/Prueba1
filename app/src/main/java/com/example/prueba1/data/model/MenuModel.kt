package com.example.prueba1.data.model

import android.icu.text.CaseMap.Title
import androidx.compose.ui.graphics.vector.ImageVector

data class MenuModel(
    val id:Int,
    val title: String,
    val option: String,
    val icon: ImageVector
)
