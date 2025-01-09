package com.example.comunicaciones.presentation.ui.screens

import android.net.http.SslError
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.comunicaciones.presentation.viewmodel.NoticiaDetailViewModel
import java.util.Calendar

@Composable
fun DetalleNoticiaScreen(
    id: Int,
    navigateBack: () -> Boolean,
    viewModel: NoticiaDetailViewModel
) {
    // Llamar al ViewModel para obtener la noticia basada en el ID
    LaunchedEffect(id) {
        viewModel.getNoticia(id)
    }

    // Observar el estado de la noticia
    val noticia by viewModel.noticia.collectAsState()

    // Mostrar la UI según el estado de la noticia
    Box(modifier = Modifier.fillMaxSize()) {
        noticia?.let { noticiaDetalle ->
            // Generar el token para la URL del video
            val token = createJWT(id)
            val videoUrl = "https://app.hcsba.cl/landing-comunicaciones/?token=$token"

            // Mostrar los datos de la noticia
            Scaffold(
                topBar = {
                    Text(
                        text = noticiaDetalle.Titulo,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            ) { paddingValues ->
                LazyColumn(
                    contentPadding = paddingValues,
                    modifier = Modifier.fillMaxSize()
                ) {
                    item {
                        NoticiaContent(videoUrl = videoUrl)
                    }
                }
            }
        } ?: run {
            // Mostrar un indicador de carga mientras se espera la noticia
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun NoticiaContent(videoUrl: String) {
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                webViewClient = object : WebViewClient() {
                    override fun onReceivedSslError(
                        view: WebView?,
                        handler: SslErrorHandler?,
                        error: SslError?
                    ) {
                        // Ignore SSL certificate errors
                        handler?.proceed()
                    }
                }
                settings.javaScriptEnabled = true
                loadUrl(videoUrl)
            }
        },
        modifier = Modifier
            .fillMaxSize()
    )
}

fun createJWT(idNoticia: Int): String {
    val algorithm = Algorithm.HMAC256("Sp1TMkvW5grBN2kpvhJr+w==")
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.HOUR, 1) // El token expira en 1 hora
    val expirationDate = calendar.time

    return JWT.create()
        .withClaim("id_noticia", idNoticia)
        .withExpiresAt(expirationDate)
        .sign(algorithm)
}