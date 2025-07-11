package org.farmetricsapp.domain.model

data class SignInCredentials(
    val email: String,
    val password: String
)

data class SignUpData(
    val email: String,
    val password: String,
    val fullName: String,
    val phoneNumber: String,
    val regionId: String,
    val districtId: String,
    val locationId: String
)

data class AuthResult(
    val success: Boolean,
    val error: String? = null,
    val officerId: String? = null
)

sealed class AuthState {
    object Initial : AuthState()
    object Loading : AuthState()
    data class Authenticated(val officerId: String) : AuthState()
    data class Error(val message: String) : AuthState()
} 