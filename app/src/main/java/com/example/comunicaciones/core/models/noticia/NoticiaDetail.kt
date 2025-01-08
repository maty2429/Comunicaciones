package com.example.comunicaciones.core.models.noticia

data class NoticiaDetalle(
    val ID: Int,
    val Titulo: String,
    val NombreImgTitulo: String,
    val ContenidoNoticia: ContenidoNoticia,
    val FechaPublicacion: String,
    val GrupoID: Int,
    val EstadoPublicacionID: Int,
    val UltActualizacion: String,
    val Props: Props,
    val LectorNoticias: List<Any>, // Ajusta el tipo si conoces la estructura de LectorNoticias
    val EstadoPublicacion: Estado,
    val Grupo: Estado,
    var imageUrl: String? = null
)

data class ContenidoNoticia(
    val blocks: List<Block>,
    val entityMap: Map<String, Entity> // Ajusta el tipo si conoces la estructura de entityMap
)

data class Block(
    val key: String,
    val data: Map<String, Any>,
    val text: String,
    val type: String,
    val depth: Int,
    val entityRanges: List<EntityRange>,
    val inlineStyleRanges: List<InlineStyleRange>
)

data class EntityRange(
    val key: Int,
    val length: Int,
    val offset: Int
)

data class Entity(
    val type: String,
    val mutability: String,
    val data: Map<String, Any>
)

data class InlineStyleRange(
    val style: String,
    val length: Int,
    val offset: Int
)

data class Props(
    val ID: Int,
    val Tags: List<String>,
    val NoticiaID: Int,
    val Destacado: Boolean,
    val Orden: Int,
    val Notificable: Boolean,
    val SubcategoriaID: Int,
    val SubCategoria: Any? // Ajusta el tipo si conoces la estructura de SubCategoria
)

data class Estado(
    val ID: Int,
    val Descripcion: String,
    val Estado: Boolean
)