package com.example.comunicaciones.core.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
fun formatFecha(fecha: String): String {
    val zonedDateTime = ZonedDateTime.parse(fecha)
    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")
    return zonedDateTime.format(formatter)
}