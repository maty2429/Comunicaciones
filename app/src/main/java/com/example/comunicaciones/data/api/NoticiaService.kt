package com.example.comunicaciones.data.api

import com.example.comunicaciones.core.models.noticia.NoticiaDetalle
import com.example.comunicaciones.core.models.noticia.NoticiaMinimalModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NoticiaService {
    @GET("noticias/getByEstadoPublicacionId/{estado}?view=m")
    suspend fun getNoticiasByEstadoPublicacionId(@Path("estado") estado: Int): Response<List<NoticiaMinimalModel>>

    @GET("noticias/getById/{id}")
    suspend fun getNoticiaById(@Path("id") id: Int): Response<NoticiaDetalle>
}