package com.suslanium.wordsfactory.data.repository

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.suslanium.wordsfactory.domain.entity.auth.LoginRequest
import com.suslanium.wordsfactory.domain.entity.auth.RegisterRequest
import com.suslanium.wordsfactory.domain.repository.AuthRepository
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl() : AuthRepository {

    private var auth: FirebaseAuth = Firebase.auth

    override suspend fun register(registerRequest: RegisterRequest) {
        auth.createUserWithEmailAndPassword(registerRequest.email, registerRequest.password)
            .addOnCompleteListener { result ->
                if (!result.isSuccessful) {
                    result.exception?.let {
                        throw it
                    }
                }
            }.await()
    }

    override suspend fun login(loginRequest: LoginRequest) {
        auth.signInWithEmailAndPassword(loginRequest.email, loginRequest.password)
            .addOnCompleteListener { result ->
                if (!result.isSuccessful) {
                    result.exception?.let {
                        throw it
                    }
                }
            }.await()
    }

}