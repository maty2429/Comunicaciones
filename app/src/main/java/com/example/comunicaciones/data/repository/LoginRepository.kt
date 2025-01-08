package com.example.comunicaciones.data.repository

import com.example.comunicaciones.core.models.Login
import com.example.comunicaciones.core.models.LoginResponse
import com.example.comunicaciones.data.api.RetrofitBuilder
import retrofit2.Response

class LoginRepository {

    suspend fun loginRepository(login:Login): Response<LoginResponse> {
        return try {
            val response = RetrofitBuilder.ApiModules.loginSerive.login(login)
            response
        } catch (e: Exception) {
            Response.error(500, null)
        }
    }
}