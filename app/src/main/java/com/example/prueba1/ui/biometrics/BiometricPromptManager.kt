package com.example.prueba1.ui.biometrics

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.PromptInfo
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
class  BiometricPromptManager(
    private val activity : AppCompatActivity
) {
    private val resultChannel = Channel<BiometricResult>()
    val promptResults = resultChannel.receiveAsFlow()
    fun showBiometricPrompt(
        title: String,
        description: String
    ) {
        val manager = BiometricManager.from(activity)
        val authenticators = if (Build.VERSION.SDK_INT >= 30) {
            BIOMETRIC_STRONG or DEVICE_CREDENTIAL
        } else BIOMETRIC_STRONG
        // Building the prompt
        val promptInfo = PromptInfo.Builder()
            .setTitle(title)
            .setDescription(description)
            .setAllowedAuthenticators(authenticators)
        if (Build.VERSION.SDK_INT < 30) {
            promptInfo.setNegativeButtonText("Cancel")
        }
        when (manager.canAuthenticate(authenticators)) {
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                resultChannel.trySend(BiometricResult.HardwareUnavailable) //We send the event
                return
            }
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                resultChannel.trySend(BiometricResult.FeatureUnavailable)
                return
            }
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                resultChannel.trySend(BiometricResult.AuthenticationNotSet)
                return
            }
            else -> Unit
        }
        //The actual prompt
        val prompt = BiometricPrompt(
            activity,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    // The only option that sends a string
                    resultChannel.trySend(BiometricResult.AuthenticationError(errString.toString()))
                }
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    resultChannel.trySend(BiometricResult.AuthenticationSuccess)
                }
                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    resultChannel.trySend(BiometricResult.AuthenticationFailed)
                }
            }
        )
        prompt.authenticate(promptInfo.build())
    }
    sealed interface BiometricResult{ // Posible results
        //In case the evaluation can be perfomed, but hardware is busy
        data object HardwareUnavailable : BiometricResult
        //Biometric evaluation isn't avaialable in the device
        data object FeatureUnavailable : BiometricResult
        //Failed not beacause the users fault
        data class AuthenticationError(val error: String): BiometricResult
        //The wrong face or the wrong finger
        data object AuthenticationFailed : BiometricResult
        // Correctly recognized
        data object AuthenticationSuccess : BiometricResult
        //In case the user doesn't have set up authentication
        data object AuthenticationNotSet : BiometricResult
    }
}