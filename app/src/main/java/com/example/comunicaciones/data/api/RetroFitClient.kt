package com.example.comunicaciones.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitBuilder {
    fun createRetrofitInstance(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create()) // Usa Gson para deserializar el JSON
            .build()
    }


    object ApiModules {
        private const val LOGIN_BASE_URL = "http://ws.hcsba.cl/isa2-tecnicos/"
        private const val NEWS_BASE_URL = "http://ws.hcsba.cl/api-microservicio-news/"
        private const val MINIO_BASE_URL = "http://ws.hcsba.cl/minio-api/v1/"


        val loginSerive: LoginService by lazy {
            RetrofitBuilder.createRetrofitInstance(LOGIN_BASE_URL).create(LoginService::class.java)
        }

        val newsService: NoticiaService by lazy {
            RetrofitBuilder.createRetrofitInstance(NEWS_BASE_URL).create(NoticiaService::class.java)
        }

        val minioService: MinioService by lazy {
            RetrofitBuilder.createRetrofitInstance(MINIO_BASE_URL).create(MinioService::class.java)
        }

    }

}