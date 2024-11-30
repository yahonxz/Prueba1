package com.example.prueba1.data.controller

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prueba1.data.model.LoginRequest
import com.example.prueba1.data.model.LoginResponse
import com.example.prueba1.data.model.User
import com.example.prueba1.data.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
class LoginViewModel : ViewModel() {
    // Estado del login observable por la UI
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()
    // Método para iniciar sesión
    fun login(username: String, password: String) {
        _loginState.value = LoginState.Loading // Indicar que el proceso ha iniciado
        viewModelScope.launch {
            try {
                // Llamada a la API
                val response = RetrofitClient.api.login(LoginRequest(username, password))
                if (response.isSuccessful) {
                    val body = response.body()
                    // Verificar si el login fue exitoso
                    if (body != null && body.login == "success" && body.user.isNotEmpty()) {
                        _loginState.value = LoginState.Success(body.user[0]) // Usuario válido
                    } else {
                        _loginState.value = LoginState.Error("Credenciales incorrectas") // Usuario o contraseña incorrectos
                    }
                } else {
                    _loginState.value = LoginState.Error("Error en la solicitud: ${response.code()}") // Error de respuesta
                }
            } catch (e: HttpException) {
                _loginState.value = LoginState.Error("Error HTTP: ${e.message}") // Error en la red
            } catch (e: IOException) {
                _loginState.value = LoginState.Error("Error de conexión: ${e.message}") // Problema de conexión
            } catch (e: Exception) {
                _loginState.value = LoginState.Error("Error desconocido: ${e.message}") // Otros errores
            }
        }
    }
}
// Estados posibles del proceso de login
sealed class LoginState {
    object Idle : LoginState() // Estado inicial
    object Loading : LoginState() // Mientras se procesa el login
    data class Success(val user: User) : LoginState() // Login exitoso
    data class Error(val message: String) : LoginState() // Error en el login
}