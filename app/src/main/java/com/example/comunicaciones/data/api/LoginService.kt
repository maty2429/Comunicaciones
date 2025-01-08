package com.example.comunicaciones.data.api

import com.example.comunicaciones.core.models.Login
import com.example.comunicaciones.core.models.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("login")
    suspend fun login(@Body login: Login): Response<LoginResponse>
}