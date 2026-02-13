package com.example.foodrecipe.ui.auth

sealed class AuthEvent {

    object NavigateToHome : AuthEvent()
    object NavigateToSignIn : AuthEvent()
    object NavigateToSignUp : AuthEvent()

    data class ShowError(val message: String) : AuthEvent()
}
