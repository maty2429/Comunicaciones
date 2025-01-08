package com.example.comunicaciones.ui.components.LastNewsCompontent

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.compose.InstitutionalGreen
import com.example.comunicaciones.R
import com.example.comunicaciones.core.models.noticia.NoticiaMinimalModel
import com.example.comunicaciones.core.utils.formatFecha


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun UltimasNoticasComponent(comunicados: List<NoticiaMinimalModel>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp) // Espacio entre los elementos
    ) {
        items(comunicados) { comunicado ->
            NoticiaCard(comunicado)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoticiaCard(comunicado: NoticiaMinimalModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .border(
                width = 2.dp,
                color = InstitutionalGreen,
                shape = RoundedCornerShape(12.dp)
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White, // Fondo blanco
            contentColor = Color.Black
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(modifier = Modifier.clickable { /* Acción al hacer clic */ }) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Imagen
                NoticiaImage(imageUrl = comunicado.imageUrl)

                Spacer(modifier = Modifier.width(12.dp)) // Espacio entre imagen y texto

                // Título y fecha
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = comunicado.Titulo,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        ),
                        modifier = Modifier.padding(end = 12.dp),
                        color = Color.Black,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Start
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Fecha
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Fecha de publicación",
                            modifier = Modifier.size(16.dp),
                            tint = Color.Gray
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = formatFecha(comunicado.FechaPublicacion) ?: "Fecha no disponible",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontSize = 12.sp,
                                color = Color.Gray
                            ),
                            textAlign = TextAlign.Start
                        )
                    }
                }
            }

            // Icono de flechas en la esquina superior derecha
            Image(
                painter = painterResource(id = R.drawable.ic_arrows_colored),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
                    .size(24.dp)
            )
        }
    }
}

@Composable
fun NoticiaImage(imageUrl: String?) {
    if (imageUrl != null) {
        Image(
            painter = rememberAsyncImagePainter(imageUrl),
            contentDescription = null,
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(8.dp))
        )
    } else {
        Image(
            painter = painterResource(id = R.drawable.placeholder), // Recurso de imagen predeterminado
            contentDescription = "Imagen no disponible",
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(8.dp))
        )
    }
}
