package com.example.comunicaciones.core.utils
import androidx.compose.ui.text.input.TextFieldValue

object FormValidatorLogin {
    fun validateForm(rut: TextFieldValue, password: TextFieldValue, onRutError: (Boolean) -> Unit, onPasswordError: (Boolean) -> Unit): Boolean {
        var isValid = true
        if (rut.text.isEmpty()) {
            onRutError(true)
            isValid = false
        } else {
            onRutError(false)
        }
        if (password.text.isEmpty()) {
            onPasswordError(true)
            isValid = false
        } else {
            onPasswordError(false)
        }
        return isValid
    }
}