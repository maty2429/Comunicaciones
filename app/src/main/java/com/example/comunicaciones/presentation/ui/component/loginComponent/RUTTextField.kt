package com.example.comunicaciones.presentation.ui.component.loginComponent

// RUTTextField.kt
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.focus.onFocusChanged
import com.example.comunicaciones.core.utils.formatRut

@Composable
fun RUTTextField(
    rut: TextFieldValue,
    onRutChange: (TextFieldValue) -> Unit,
    rutError: Boolean,
    hasInteractedWithRut: Boolean,
    onRutErrorChange: (Boolean) -> Unit
) {
    OutlinedTextField(
        value = rut,
        onValueChange = {
            onRutChange(it)
            val filteredRut = it.text.filter { it.isDigit() || it.equals('K', ignoreCase = true) }
            if (filteredRut != it.text) {
                onRutChange(TextFieldValue(filteredRut, TextRange(filteredRut.length)))
            }
            onRutErrorChange(filteredRut.isEmpty() && hasInteractedWithRut)
        },
        label = { Text("RUT") },
        isError = rutError,
        leadingIcon = { Icon(Icons.Default.Person, contentDescription = "RUT Icon") },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = if (rutError) Color.Red else Color.Gray,
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            unfocusedLabelColor = if (rutError) Color.Red else Color.Gray
        ),
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged { focusState ->
                if (focusState.isFocused) {
                    val cleanedRut = rut.text.replace(".", "").replace("-", "")
                    onRutChange(rut.copy(
                        text = cleanedRut,
                        selection = TextRange(cleanedRut.length)
                    ))
                } else {
                    val formattedRut = formatRut(rut.text)
                    onRutChange(rut.copy(
                        text = formattedRut,
                        selection = TextRange(formattedRut.length)
                    ))
                    onRutErrorChange(formattedRut.isEmpty() && hasInteractedWithRut)
                }
            },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        singleLine = true
    )
}