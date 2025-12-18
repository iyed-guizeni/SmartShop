package com.example.smartshop.authentication

data class AuthUiState (
    val loading : Boolean = false,
    val success : Boolean = false,
    val error : String? = null
)