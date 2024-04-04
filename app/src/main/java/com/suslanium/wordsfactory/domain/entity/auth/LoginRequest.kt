package com.suslanium.wordsfactory.domain.entity.auth

data class LoginRequest(
    val email: String,
    val password: String
)
