package org.farmetricsapp.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.farmetricsapp.domain.model.*
import org.farmetricsapp.domain.repository.AuthRepository
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    
    private val _state = MutableStateFlow<AuthState>(AuthState.Initial)
    val state: StateFlow<AuthState> = _state
    
    fun signIn(credentials: SignInCredentials) {
        viewModelScope.launch {
            _state.update { AuthState.Loading }
            
            try {
                val result = authRepository.signIn(credentials)
                if (result.success) {
                    _state.update { AuthState.Authenticated(result.officerId!!) }
                } else {
                    _state.update { AuthState.Error(result.error ?: "Unknown error occurred") }
                }
            } catch (e: Exception) {
                _state.update { AuthState.Error(e.message ?: "Unknown error occurred") }
            }
        }
    }
    
    fun signUp(data: SignUpData) {
        viewModelScope.launch {
            _state.update { AuthState.Loading }
            
            try {
                val result = authRepository.signUp(data)
                if (result.success) {
                    _state.update { AuthState.Authenticated(result.officerId!!) }
                } else {
                    _state.update { AuthState.Error(result.error ?: "Unknown error occurred") }
                }
            } catch (e: Exception) {
                _state.update { AuthState.Error(e.message ?: "Unknown error occurred") }
            }
        }
    }
    
    fun resetPassword(email: String) {
        viewModelScope.launch {
            _state.update { AuthState.Loading }
            
            try {
                val result = authRepository.resetPassword(email)
                if (result.success) {
                    _state.update { AuthState.Initial }
                } else {
                    _state.update { AuthState.Error(result.error ?: "Unknown error occurred") }
                }
            } catch (e: Exception) {
                _state.update { AuthState.Error(e.message ?: "Unknown error occurred") }
            }
        }
    }
    
    fun signOut() {
        viewModelScope.launch {
            _state.update { AuthState.Loading }
            
            try {
                authRepository.signOut()
                _state.update { AuthState.Initial }
            } catch (e: Exception) {
                _state.update { AuthState.Error(e.message ?: "Unknown error occurred") }
            }
        }
    }
    
    fun clearError() {
        _state.update { AuthState.Initial }
    }
} 