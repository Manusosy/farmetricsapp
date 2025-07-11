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
import org.farmetricsapp.ui.components.auth.*

@Composable
fun ForgotPasswordScreen(
    viewModel: AuthViewModel,
    onNavigateToLogin: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    var email by remember { mutableStateOf("") }
    var resetSent by remember { mutableStateOf(false) }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
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
                    isError = state.error?.contains("email", ignoreCase = true) == true,
                    errorMessage = if (state.error?.contains("email", ignoreCase = true) == true) {
                        state.error
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
                    enabled = email.isNotBlank(),
                    isLoading = state.isLoading
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
                    enabled = !state.isLoading,
                    isLoading = state.isLoading
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            AuthDivider(text = "OR")
            
            Spacer(modifier = Modifier.height(24.dp))
            
            OutlinedButton(
                onClick = onNavigateToLogin,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = ButtonShape
            ) {
                Text("Back to Sign In")
            }
            
            Spacer(modifier = Modifier.height(32.dp))
        }
        
        // Error snackbar
        AnimatedVisibility(
            visible = state.error != null && !state.error.contains("email", ignoreCase = true),
            enter = slideInVertically { it } + fadeIn(),
            exit = slideOutVertically { it } + fadeOut(),
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            state.error?.let { error ->
                Snackbar(
                    modifier = Modifier.padding(16.dp),
                    action = {
                        TextButton(onClick = { viewModel.clearError() }) {
                            Text("Dismiss")
                        }
                    }
                ) {
                    Text(error)
                }
            }
        }
    }
} 