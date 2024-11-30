package com.example.prueba1.data.model

data class LoginResponse(
    val login: String,
    val user: List<User>
)
data class LoginRequest(
    val username: String,
    val password: String
)
data class User(
    val id: Int,
    val name: String,
    val username: String,
    val password: String
)
