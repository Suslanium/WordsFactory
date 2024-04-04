package com.suslanium.wordsfactory.domain.entity.auth

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)
