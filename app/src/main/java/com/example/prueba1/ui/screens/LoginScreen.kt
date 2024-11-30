package com.example.prueba1.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.prueba1.data.controller.LoginState
import com.example.prueba1.data.controller.LoginViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = viewModel() // Vinculamos el ViewModel
) {
    // Observamos el estado del login
    val loginState by viewModel.loginState.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        LoginForm(navController, viewModel, loginState)
    }
}

@Preview(showBackground = true)
@Composable
fun ShowLoginForm() {
    LoginForm(
        navController = rememberNavController(),
        viewModel = LoginViewModel(),
        loginState = LoginState.Idle
    )
}

@Composable
fun LoginForm(
    navController: NavController,
    viewModel: LoginViewModel,
    loginState: LoginState
) {
    var user by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Card(
        colors = CardDefaults.cardColors(
            contentColor = Color.White,
            containerColor = Color.DarkGray
        ),
        modifier = Modifier
            .padding(40.dp, 0.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
        ) {
            // Cargar recursos desde una URL
            AsyncImage(
                model = "https://logowik.com/content/uploads/images/github-colored9940.logowik.com.webp",
                contentDescription = "Github logo",
                contentScale = ContentScale.Fit
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                maxLines = 1,
                value = user,
                onValueChange = { user = it },
                label = { Text("User") }
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                maxLines = 1,
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation()
            )

            FilledTonalButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp),
                onClick = {
                    viewModel.login(user, password) // Llama al login del ViewModel
                }
            ) {
                Text("LOG IN")
            }

            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp),
                onClick = {
                    navController.navigate("createAccount")
                }
            ) {
                Text("CREATE AN ACCOUNT")
            }

            // Mostrar estado del login
            when (loginState) {
                is LoginState.Idle -> Text("Please enter your credentials")
                is LoginState.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                is LoginState.Success -> {
                    LaunchedEffect(Unit) {
                        navController.navigate("home") // Navega al home después de un login exitoso
                    }
                }
                is LoginState.Error -> Text(
                    text = (loginState as LoginState.Error).message,
                    color = Color.Red,
                    modifier = Modifier.padding(top = 10.dp)
                )
            }
        }
    }
}