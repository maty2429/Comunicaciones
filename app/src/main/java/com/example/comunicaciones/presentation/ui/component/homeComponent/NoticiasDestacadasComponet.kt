package com.example.comunicaciones.presentation.ui.component.homeComponent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.compose.InstitutionalBlue
import com.example.compose.InstitutionalGreen
import com.example.compose.InstitutionalOrange
import com.example.comunicaciones.R
import com.example.comunicaciones.core.models.noticia.NoticiaMinimalModel
import kotlinx.coroutines.delay
import kotlin.collections.count
import kotlin.to


val multiColorGradient = Brush.linearGradient(
    colorStops = arrayOf(
        0.0f to InstitutionalOrange,  // Inicio con naranja
        0.6f to InstitutionalGreen,   // Verde se extiende aún más
        0.8f to InstitutionalBlue     // Azul comienza aún más tarde
    ),
    start = Offset(0f, 0f),  // Comienza desde la esquina superior izquierda
    end = Offset(700f, 700f) // Ajusta el final según el tamaño
)

@Composable
fun NoticasDestacadasComponent(comunicados: List<NoticiaMinimalModel>) {
    if (comunicados.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentAlignment = Alignment.Companion.Center
        ) {
            Text(text = "Cargando imágenes...", style = MaterialTheme.typography.bodyMedium)
        }
    } else {
        val pagerState = rememberPagerState(initialPage = 0, pageCount = { comunicados.size })

        // Efecto para cambiar automáticamente la imagen cada 3 segundos
        LaunchedEffect(pagerState) {
            while (true) {
                delay(5000L) // Cambiar imagen cada 3 segundos
                val nextPage = (pagerState.currentPage + 1) % comunicados.count()
                pagerState.animateScrollToPage(nextPage) // Animar el scroll a la siguiente página
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(320.dp) // Ajuste de altura
                .border(6.dp, multiColorGradient, RoundedCornerShape(24.dp))
                .clip(androidx.compose.foundation.shape.RoundedCornerShape(24.dp)) // Esquinas más redondeadas
                .shadow(
                    4.dp,
                    androidx.compose.foundation.shape.RoundedCornerShape(24.dp)
                ) // Sombras suaves
        ) { page ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(320.dp)
                    .clickable {
                    }
            ) {
                // Imagen de fondo
                Image(
                    painter = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(comunicados[page].imageUrl)
                            .decoderFactory(SvgDecoder.Factory())
                            .placeholder(R.drawable.hcsba)  // Placeholder
                            .error(R.drawable.hcsba)       // Imagen de error
                            .build()
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.Companion.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Título del comunicado en la parte inferior con fondo semi-transparente
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .fillMaxWidth()
                        .background(
                            Color.Black.copy(alpha = 0.5f) // Fondo semi-transparente ajustado
                        )
                        .padding(horizontal = 16.dp, vertical = 10.dp) // Espaciado del título
                ) {
                    Text(
                        text = comunicados[page].Titulo,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp // Tamaño de fuente ajustado
                        )
                    )
                }
            }
        }
    }
}
