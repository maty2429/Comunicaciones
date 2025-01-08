package com.example.comunicaciones.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.comunicaciones.R
import com.example.comunicaciones.core.utils.FormValidatorLogin
import com.example.comunicaciones.presentation.ui.component.loginComponent.PasswordTextField
import com.example.comunicaciones.presentation.ui.component.loginComponent.RUTTextField
import com.example.comunicaciones.presentation.viewmodel.LoginUiState
import com.example.comunicaciones.presentation.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    navigateToHomeScreen: (String?) -> Unit,
    viewModel: LoginViewModel
) {

    val uiState by viewModel.uiState.collectAsState()

    var rut by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var rutError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var hasInteractedWithRut by remember { mutableStateOf(false) }

    var showErrorDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    fun validateForm(): Boolean {
        return FormValidatorLogin.validateForm(
            rut,
            password,
            { rutError = it },
            { passwordError = it })
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.hcsba),
                contentDescription = "Logo",
                modifier = Modifier
                    .height(200.dp)
                    .padding(bottom = 24.dp)
            )
            Text(
                text = "Comunicaciones",
                fontSize = 20.sp,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "Iniciar sesión",
                fontSize = 24.sp,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            RUTTextField(
                rut = rut,
                onRutChange = { rut = it },
                rutError = rutError,
                hasInteractedWithRut = hasInteractedWithRut,
                onRutErrorChange = { rutError = it }
            )

            LaunchedEffect(rut.text) {
                if (!hasInteractedWithRut && rut.text.isNotEmpty()) {
                    hasInteractedWithRut = true
                }
            }
            if (rutError) {
                Text(
                    text = "RUT es requerido",
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            PasswordTextField(
                password = password,
                onPasswordChange = { password = it },
                passwordError = passwordError,
                onPasswordErrorChange = { passwordError = it }
            )
            if (passwordError) {
                Text(
                    text = "Contraseña es requerida",
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (validateForm()) {
                        viewModel.login(rut.text, password.text)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Iniciar Sesión")
            }

            LaunchedEffect(uiState){
                if (uiState is LoginUiState.Success) {
                    navigateToHomeScreen((uiState as LoginUiState.Success).token)
                } else if (uiState is LoginUiState.Error) {
                    errorMessage = (uiState as LoginUiState.Error).message
                    showErrorDialog = true
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            if (uiState is LoginUiState.Loading) {
                CircularProgressIndicator()
            }

            if (showErrorDialog) {
                AlertDialog(
                    onDismissRequest = { showErrorDialog = false },
                    title = { Text("Error") },
                    text = { Text(errorMessage) },
                    confirmButton = {
                        Button(onClick = { showErrorDialog = false }) {
                            Text("Aceptar")
                        }
                    }
                )
            }
        }
    }
}
