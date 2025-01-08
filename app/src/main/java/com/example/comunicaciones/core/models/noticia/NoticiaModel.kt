package com.example.comunicaciones.core.models.noticia

data class NoticiaMinimalModel(
    val ID: Int,
    val EstadoPublicacionID: Int,
    val FechaPublicacion: String,
    val GrupoID: Int,
    val NombreImgTitulo: String,
    val Props: PropsModel,
    val Titulo: String,
    var imageUrl: String? = null // Añadir un campo para la URL de la imagen
)

data class PropsModel(
    val ID: Int,
    val Tags: Array<String>,
    val NoticiaID: Int,
    val Destacado: Boolean,
    val Orden: Int,
    val Notificable: Boolean,
    val SubcategoriaID: Int
)