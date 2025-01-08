package com.example.comunicaciones.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comunicaciones.core.models.Login
import com.example.comunicaciones.data.repository.LoginRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val newRepositoryLogin = LoginRepository()

    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val uiState: StateFlow<LoginUiState> = _uiState


    fun login(rut: String, password: String) {
        viewModelScope.launch {
            _uiState.value = LoginUiState.Loading
            try {
                val hashedPassword = hashPassword(password)
                val response = newRepositoryLogin.loginRepository(Login(rut, hashedPassword))
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    println("Response body: $responseBody")
                    if (responseBody != null) {
                        val token = responseBody.token
                        _uiState.value = LoginUiState.Success(token)
                    } else {
                        println("Error: Response body is null")
                        _uiState.value = LoginUiState.Error("Respuesta del servidor vacía")
                    }
                } else {
                    println("Error: Response is not successful, code: ${response.code()}, message: ${response.message()}")
                    _uiState.value = LoginUiState.Error("Credenciales incorrectas")
                }
            } catch (e: Exception) {
                println("Error desconocido: ${e.message}")
                _uiState.value = LoginUiState.Error("Error desconocido")
            }
        }
    }

    private fun hashPassword(password: String): String {
        val bytes = java.security.MessageDigest.getInstance("MD5").digest(password.toByteArray())
        return bytes.joinToString("") { "%02x".format(it) }
    }
}


sealed class LoginUiState {
    data object Idle : LoginUiState()
    data object Loading : LoginUiState()
    data class Success(val token: String) : LoginUiState()
    data class Error(val message: String) : LoginUiState()
}