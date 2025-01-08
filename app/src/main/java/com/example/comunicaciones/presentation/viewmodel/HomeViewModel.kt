package com.example.comunicaciones.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comunicaciones.core.models.noticia.NoticiaMinimalModel
import com.example.comunicaciones.data.repository.NoticiaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response


class HomeViewModel : ViewModel() {
    private val noticiaRepository = NoticiaRepository()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> get() = _isRefreshing

    var noticias =
        MutableStateFlow<List<NoticiaMinimalModel>>(emptyList())
        private set


    init {
        getNoticias()
    }


    private fun getNoticias() {
        viewModelScope.launch {
            try {
                val response = noticiaRepository.getNoticiasByEstadoPublicacionId(2)
                if (response.isSuccessful) {
                    noticias.value = response.body() ?: emptyList()
                    println("Response: ${response.body()}")
                } else {
                    println("Error: ${response.errorBody()}")
                }
            } catch (e: Exception) {
                println("Error al obtener las noticias")
            }
        }
    }

    fun refreshData(onComplete: () -> Unit) {
        viewModelScope.launch {
            try {
                _isRefreshing.value = true
                // Simulate loading new data
                //delay(2000)  // Simulate a 2 second delay for refreshing data
                getNoticias()
            } catch (e: Exception) {
                Log.e("HomePageViewModel", "Error al refrescar los datos", e)
            } finally {
                _isRefreshing.value = false
                onComplete()
            }
        }
    }

}