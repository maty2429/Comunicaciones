package com.example.comunicaciones.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.comunicaciones.presentation.ui.screens.DetalleNoticiaScreen
import com.example.comunicaciones.presentation.ui.screens.HomeScreen
import com.example.comunicaciones.presentation.ui.screens.LoginScreen
import com.example.comunicaciones.presentation.viewmodel.HomeViewModel
import com.example.comunicaciones.presentation.viewmodel.LoginViewModel
import com.example.comunicaciones.presentation.viewmodel.NoticiaDetailViewModel

@RequiresApi(value = 26)
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
                val homeScreenViewModel = HomeViewModel()
                HomeScreen(
                    viewModel = homeScreenViewModel,
                    navigateToDetalleNoticiaScreen = { id ->
                        navController.navigate(
                            DetalleNoticiaScreenDes(
                                id = id
                            )
                        )
                    }
                )
            }

            composable<DetalleNoticiaScreenDes> { backStackEntry ->
                val id: DetalleNoticiaScreenDes = backStackEntry.toRoute()
                val detalleNoticiaViewModel = NoticiaDetailViewModel()
                DetalleNoticiaScreen(
                    id = id.id,
                    viewModel = detalleNoticiaViewModel,
                    navigateBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}