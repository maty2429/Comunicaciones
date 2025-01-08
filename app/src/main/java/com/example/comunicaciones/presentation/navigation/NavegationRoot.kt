package com.example.comunicaciones.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.comunicaciones.presentation.ui.screens.HomeScreen
import com.example.comunicaciones.presentation.ui.screens.LoginScreen
import com.example.comunicaciones.presentation.viewmodel.LoginViewModel

@Composable
fun NavigationRoot(
    navController: NavHostController
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        NavHost(
            navController = navController,
            startDestination = LoginScreenDes
        ) {
            composable<LoginScreenDes> {
                val loginScreenViewModel = LoginViewModel()
                LoginScreen(
                    viewModel = loginScreenViewModel,
                    navigateToHomeScreen = {
                        navController.navigate(HomeScreenDes)
                    }
                )
            }

            composable<HomeScreenDes> {
                HomeScreen()
            }
        }
    }
}