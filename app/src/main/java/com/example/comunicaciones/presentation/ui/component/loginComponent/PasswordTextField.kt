package com.example.comunicaciones.presentation.ui.component.loginComponent

// PasswordTextField.kt
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun PasswordTextField(
    password: TextFieldValue,
    onPasswordChange: (TextFieldValue) -> Unit,
    passwordError: Boolean,
    onPasswordErrorChange: (Boolean) -> Unit
) {
    OutlinedTextField(
        value = password,
        onValueChange = {
            onPasswordChange(it)
            onPasswordErrorChange(it.text.isEmpty())
        },
        label = { Text("Contraseña") },
        isError = passwordError,
        visualTransformation = PasswordVisualTransformation(),
        leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Password Icon") },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = if (passwordError) Color.Red else Color.Gray,
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            unfocusedLabelColor = if (passwordError) Color.Red else Color.Gray
        ),
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.fillMaxWidth(),
        singleLine = true
    )
}