package org.farmetricsapp.domain.repository

import org.farmetricsapp.domain.model.AuthResult
import org.farmetricsapp.domain.model.SignInCredentials
import org.farmetricsapp.domain.model.SignUpData

interface AuthRepository {
    suspend fun signIn(credentials: SignInCredentials): AuthResult
    
    suspend fun signUp(data: SignUpData): AuthResult
    
    suspend fun resetPassword(email: String): AuthResult
    
    suspend fun signOut()
    
    suspend fun getCurrentOfficerId(): String?
} 