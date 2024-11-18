package com.example.prueba1.ui.biometrics

import android.content.Intent
import android.hardware.biometrics.BiometricManager.Authenticators.BIOMETRIC_STRONG
import android.hardware.biometrics.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
@Composable
fun BiometricsScreen(navController: NavController,activity:AppCompatActivity){
    val promptManager  by lazy{
        /**
         * by lazy means we initialize the value as soon
         * as we access it the fist time
         */
        BiometricPromptManager(activity)
    }
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        //Access the flow to collect events
        val biometricResult by promptManager.promptResults.collectAsState(initial = null)
        // In order to fire and launch the activity
        // to set a biometric or enroll it
        val enrollLauncher = rememberLauncherForActivityResult(
            // We fire the StartActivityFor result to pop up an
            // activity where the user can choose a pattern
            contract = ActivityResultContracts.StartActivityForResult(),
            onResult = {
                println("Activity result: $it")
            }
        )
        // To prompt the user to set a biometric
        // signature in case it hasn't been set
        LaunchedEffect(biometricResult) {
            if(biometricResult is BiometricPromptManager.BiometricResult.AuthenticationNotSet){
                if(Build.VERSION.SDK_INT >= 30){
                    val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                        putExtra(
                            Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                            BIOMETRIC_STRONG or DEVICE_CREDENTIAL
                            // Alt + Enter -> import that
                        )
                    }
                    // Fire the activity
                    enrollLauncher.launch(enrollIntent)
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Button(onClick = {navController.popBackStack()}){
                Icon(Icons.Filled.KeyboardArrowLeft,"Go Back")
            }
            //Simple button
            Button(onClick = {
                promptManager.showBiometricPrompt(
                    title = "Sample prompt",
                    description = "Sample prompt description"
                )
            }) {
                Text(text = "Authenticate")
            }
            biometricResult?.let{ //like an if after the event
                    result -> // Do something according to the result
                Text(
                    text = when(result){
                        //Alt + Enter -> Add remaining branches
                        //Select the first sentence before the dot
                        // Alt+ Enter -> import memembers from ....
                        is BiometricPromptManager.BiometricResult.AuthenticationError -> {
                            result.error
                        }
                        BiometricPromptManager.BiometricResult.AuthenticationFailed -> {
                            "Authentication failed"
                        }
                        BiometricPromptManager.BiometricResult.AuthenticationNotSet -> {
                            "Authentication not set"
                        }
                        BiometricPromptManager.BiometricResult.AuthenticationSuccess -> {
                            "Authentication success"
                        }
                        BiometricPromptManager.BiometricResult.FeatureUnavailable -> {
                            "Feature unavailable"
                        }
                        BiometricPromptManager.BiometricResult.HardwareUnavailable -> {
                            "Hardware unavailable"
                        }
                    }
                )
            }
        }
    }
}