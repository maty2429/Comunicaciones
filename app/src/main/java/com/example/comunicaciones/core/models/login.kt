package com.example.comunicaciones.core.models

data class Login(
    val username: String,
    val password: String
)

data class LoginResponse(
    val mensaje: String,
    val rut: String,
    val token: String
)

data class ActializarContrasena(
    val Rut: String,
    val Contrasena: String,
    val ContrasenaNueva: String
)