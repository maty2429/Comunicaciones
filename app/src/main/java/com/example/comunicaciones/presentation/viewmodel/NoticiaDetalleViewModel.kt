package com.example.comunicaciones.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comunicaciones.core.models.noticia.NoticiaDetalle
import com.example.comunicaciones.data.repository.NoticiaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class NoticiaDetailViewModel : ViewModel() {
    private val noticiaDestalleRepository = NoticiaRepository()

    private val _noticia = MutableStateFlow<NoticiaDetalle?>(null)
    val noticia: MutableStateFlow<NoticiaDetalle?> get() = _noticia


    fun getNoticia(id: Int) {
        viewModelScope.launch {
            try {
                val response = noticiaDestalleRepository.getNoticiaById(id)
                if (response.isSuccessful) {
                    _noticia.value = response.body()
                    Log.d("NoticiaDetailViewModel", "Noticia cargada: ${response.body()}")
                } else {
                    Log.e("NoticiaDetailViewModel", "Error: ${response.code()} - ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("NoticiaDetailViewModel", "Error al obtener la noticia", e)
            }
        }
    }
}