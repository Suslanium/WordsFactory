package com.suslanium.wordsfactory.data.repository

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.auth
import com.google.firebase.auth.userProfileChangeRequest
import com.suslanium.wordsfactory.domain.entity.auth.LoginRequest
import com.suslanium.wordsfactory.domain.entity.auth.RegisterRequest
import com.suslanium.wordsfactory.domain.entity.auth.exception.LoginCredentialsException
import com.suslanium.wordsfactory.domain.entity.auth.exception.RegistrationCollisionException
import com.suslanium.wordsfactory.domain.repository.AuthRepository
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl : AuthRepository {

    private var auth: FirebaseAuth = Firebase.auth

    override suspend fun register(registerRequest: RegisterRequest) {
        try {
            auth.createUserWithEmailAndPassword(registerRequest.email, registerRequest.password)
                .await()

            auth.currentUser?.updateProfile(userProfileChangeRequest {
                displayName = registerRequest.name
            })?.await()
        } catch (e: Exception) {
            when (e) {
                is FirebaseAuthUserCollisionException -> {
                    throw RegistrationCollisionException()
                }

                else -> {
                    throw e
                }
            }
        }

    }

    override suspend fun login(loginRequest: LoginRequest) {
        try {
            auth.signInWithEmailAndPassword(loginRequest.email, loginRequest.password).await()
        } catch (e: Exception) {
            when (e) {
                is FirebaseAuthInvalidCredentialsException -> {
                    throw LoginCredentialsException()
                }

                else -> {
                    throw e
                }
            }
        }
    }

    override suspend fun checkUserLoggedIn(): Boolean = auth.currentUser != null

}