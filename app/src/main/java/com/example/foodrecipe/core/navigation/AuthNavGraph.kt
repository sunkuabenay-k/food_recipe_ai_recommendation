package com.example.foodrecipe.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.foodrecipe.ui.auth.SignInScreen
import com.example.foodrecipe.ui.auth.SignUpScreen

fun androidx.navigation.NavGraphBuilder.authNavGraph(
    navController: NavController
) {

    navigation(
        startDestination = Screen.SignIn.route,
        route = Screen.AuthGraph.route
    ) {

        composable(Screen.SignIn.route) {
            SignInScreen(
                onNavigateHome = {
                    navController.navigate(Screen.MainGraph.route) {
                        popUpTo(Screen.AuthGraph.route) { inclusive = true }
                    }
                },
                onNavigateToSignUp = {
                    navController.navigate(Screen.SignUp.route)
                }
            )
        }

        composable(Screen.SignUp.route) {
            SignUpScreen(
                onNavigateHome = {
                    navController.navigate(Screen.MainGraph.route) {
                        popUpTo(Screen.AuthGraph.route) { inclusive = true }
                    }
                },
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
