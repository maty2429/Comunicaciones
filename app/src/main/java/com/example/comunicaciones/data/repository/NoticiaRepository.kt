package com.example.comunicaciones.data.repository

import com.example.comunicaciones.core.models.noticia.NoticiaDetalle
import com.example.comunicaciones.core.models.noticia.NoticiaMinimalModel
import com.example.comunicaciones.data.api.RetrofitBuilder
import retrofit2.Response

class NoticiaRepository {

    suspend fun getNoticiasByEstadoPublicacionId(estado: Int): Response<List<NoticiaMinimalModel>> {
        return try {
            val noticiasResponse =
                RetrofitBuilder.ApiModules.newsService.getNoticiasByEstadoPublicacionId(estado)
            if (noticiasResponse.isSuccessful) {
                val noticias = noticiasResponse.body() ?: emptyList()
                val noticiasConUrl = noticias.map { noticia ->
                    val minioResponse =
                        RetrofitBuilder.ApiModules.minioService.getMinioImageUrl(noticia.NombreImgTitulo)
                    if (minioResponse.isSuccessful) {
                        noticia.imageUrl = minioResponse.body()?.url
                    }
                    noticia
                }
                Response.success(noticiasConUrl)
            } else {
                Response.error(noticiasResponse.code(), noticiasResponse.errorBody()!!)
            }
        } catch (e: Exception) {
            Response.error(500, null) // Manejo de errores genéricos
        }
    }

    suspend fun getNoticiaById(id: Int): Response<NoticiaDetalle> {
        return try {
            val noticiaResponse = RetrofitBuilder.ApiModules.newsService.getNoticiaById(id)
            if (noticiaResponse.isSuccessful) {
                val noticia = noticiaResponse.body()
                if (noticia != null) {
                    val minioResponse =
                        RetrofitBuilder.ApiModules.minioService.getMinioImageUrl(
                            noticia.NombreImgTitulo ?: ""
                        )
                    if (minioResponse.isSuccessful) {
                        noticia.imageUrl = minioResponse.body()?.url
                    }
                }
                Response.success(noticia)
            } else {
                Response.error(noticiaResponse.code(), noticiaResponse.errorBody()!!)
            }
        } catch (e: Exception) {
            Response.error(500, null)
        }
    }
}