package com.example.comunicaciones.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MinioService {
    @GET("file/comunicaciones/{nombreImagen}")
    suspend fun getMinioImageUrl(@Path("nombreImagen") nombreImagen: String): Response<MinioImageResponse>
}

data class MinioImageResponse(val url: String)