package com.example.prueba1.ui.location.model

data class GoogleGeoResult(
    val results: List<Results>
)
data class Results(
    val geometry: Geometry,
    val formatted_address: String
)
data class Geometry(
    val location: Location
)
data class Location(
    val lat: Double,
    val lng: Double
)
