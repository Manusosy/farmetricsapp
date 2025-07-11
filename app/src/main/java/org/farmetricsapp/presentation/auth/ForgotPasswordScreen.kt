package org.farmetricsapp.presentation.auth

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.farmetricsapp.ui.components.auth.*
import org.farmetricsapp.domain.model.AuthState

@Composable
fun ForgotPasswordScreen(
    onNavigateBack: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val authState by viewModel.state.collectAsState()
    var email by remember { mutableStateOf("") }
    var resetSent by remember { mutableStateOf(false) }
    
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(48.dp))
            
            AuthHeader(
                title = "Reset Password",
                subtitle = if (!resetSent) {
                    "Enter your email to receive reset instructions"
                } else {
                    "Check your email for reset instructions"
                }
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            
            if (!resetSent) {
                AuthTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = "Email",
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Done,
                    isError = authState is AuthState.Error && authState.message.contains("email", ignoreCase = true),
                    errorMessage = if (authState is AuthState.Error && authState.message.contains("email", ignoreCase = true)) {
                        authState.message
                    } else null,
                    onImeAction = {
                        if (email.isNotBlank()) {
                            viewModel.resetPassword(email)
                            resetSent = true
                        }
                    }
                )
                
                Spacer(modifier = Modifier.height(32.dp))
                
                AuthButton(
                    text = "Send Reset Instructions",
                    onClick = {
                        viewModel.resetPassword(email)
                        resetSent = true
                    },
                    enabled = email.isNotBlank() && authState !is AuthState.Loading,
                    isLoading = authState is AuthState.Loading
                )
            } else {
                Text(
                    text = "We've sent password reset instructions to your email. " +
                          "Please check your inbox and follow the instructions to reset your password.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                
                Spacer(modifier = Modifier.height(32.dp))
                
                AuthButton(
                    text = "Resend Instructions",
                    onClick = {
                        viewModel.resetPassword(email)
                    },
                    enabled = authState !is AuthState.Loading,
                    isLoading = authState is AuthState.Loading
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            AuthDivider(text = "OR")
            
            Spacer(modifier = Modifier.height(24.dp))
            
            OutlinedButton(
                onClick = onNavigateBack,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Back to Sign In")
            }
            
            Spacer(modifier = Modifier.height(32.dp))
        }
        
        // Error snackbar
        AnimatedVisibility(
            visible = authState is AuthState.Error && !authState.message.contains("email", ignoreCase = true),
            enter = slideInVertically { it } + fadeIn(),
            exit = slideOutVertically { it } + fadeOut(),
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            if (authState is AuthState.Error) {
                Snackbar(
                    modifier = Modifier.padding(16.dp),
                    action = {
                        TextButton(onClick = { viewModel.clearError() }) {
                            Text("Dismiss")
                        }
                    }
                ) {
                    Text(authState.message)
                }
            }
        }
    }
} 