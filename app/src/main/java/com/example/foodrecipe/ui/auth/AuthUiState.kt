package com.example.foodrecipe.ui.auth

data class AuthUiState(

    val name: String = "",
    val email: String = "",
    val password: String = "",

    val isLoading: Boolean = false,

    val error: String? = null
)
