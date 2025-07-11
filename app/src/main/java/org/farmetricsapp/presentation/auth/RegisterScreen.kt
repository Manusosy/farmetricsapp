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
import org.farmetricsapp.domain.model.SignUpData

@Composable
fun RegisterScreen(
    viewModel: AuthViewModel,
    onNavigateToLogin: () -> Unit,
    onRegisterSuccess: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var selectedRegionId by remember { mutableStateOf("") }
    var selectedDistrictId by remember { mutableStateOf("") }
    var selectedLocationId by remember { mutableStateOf("") }
    
    var passwordError by remember { mutableStateOf<String?>(null) }
    var confirmPasswordError by remember { mutableStateOf<String?>(null) }
    var phoneNumberError by remember { mutableStateOf<String?>(null) }
    
    LaunchedEffect(state.isAuthenticated) {
        if (state.isAuthenticated) {
            onRegisterSuccess()
        }
    }
    
    // Validate passwords match and phone number format
    LaunchedEffect(password, confirmPassword, phoneNumber) {
        if (password.isNotBlank() && confirmPassword.isNotBlank()) {
            if (password.length < 8) {
                passwordError = "Password must be at least 8 characters"
            } else {
                passwordError = null
            }
            
            if (password != confirmPassword) {
                confirmPasswordError = "Passwords do not match"
            } else {
                confirmPasswordError = null
            }
        }
        
        if (phoneNumber.isNotBlank()) {
            // Ghana phone number format: +233XXXXXXXXX or 0XXXXXXXXX
            val phoneRegex = """^(\+233|0)[2-9][0-9]{8}$""".toRegex()
            if (!phoneRegex.matches(phoneNumber)) {
                phoneNumberError = "Invalid Ghana phone number format"
            } else {
                phoneNumberError = null
            }
        }
    }
    
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
                title = "Create Account",
                subtitle = "Join us to start managing farms"
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            
            AuthTextField(
                value = fullName,
                onValueChange = { fullName = it },
                label = "Full Name",
                imeAction = ImeAction.Next,
                isError = state.error?.contains("name", ignoreCase = true) == true,
                errorMessage = if (state.error?.contains("name", ignoreCase = true) == true) {
                    state.error
                } else null
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            AuthTextField(
                value = email,
                onValueChange = { email = it },
                label = "Email",
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next,
                isError = state.error?.contains("email", ignoreCase = true) == true,
                errorMessage = if (state.error?.contains("email", ignoreCase = true) == true) {
                    state.error
                } else null
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            AuthTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = "Phone Number (e.g., +233XXXXXXXXX)",
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next,
                isError = phoneNumberError != null || state.error?.contains("phone", ignoreCase = true) == true,
                errorMessage = phoneNumberError ?: if (state.error?.contains("phone", ignoreCase = true) == true) {
                    state.error
                } else null
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            LocationDropdowns(
                selectedRegionId = selectedRegionId,
                selectedDistrictId = selectedDistrictId,
                selectedLocationId = selectedLocationId,
                onRegionSelected = { selectedRegionId = it },
                onDistrictSelected = { selectedDistrictId = it },
                onLocationSelected = { selectedLocationId = it },
                isError = state.error?.contains("location", ignoreCase = true) == true,
                errorMessage = if (state.error?.contains("location", ignoreCase = true) == true) {
                    state.error
                } else null
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            AuthPasswordField(
                value = password,
                onValueChange = { password = it },
                label = "Password",
                imeAction = ImeAction.Next,
                isError = passwordError != null,
                errorMessage = passwordError
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            AuthPasswordField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = "Confirm Password",
                isError = confirmPasswordError != null,
                errorMessage = confirmPasswordError,
                onImeAction = {
                    if (isFormValid()) {
                        viewModel.signUp(
                            SignUpData(
                                email = email,
                                password = password,
                                fullName = fullName,
                                phoneNumber = phoneNumber,
                                regionId = selectedRegionId,
                                districtId = selectedDistrictId,
                                locationId = selectedLocationId
                            )
                        )
                    }
                }
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            
            AuthButton(
                text = "Create Account",
                onClick = {
                    viewModel.signUp(
                        SignUpData(
                            email = email,
                            password = password,
                            fullName = fullName,
                            phoneNumber = phoneNumber,
                            regionId = selectedRegionId,
                            districtId = selectedDistrictId,
                            locationId = selectedLocationId
                        )
                    )
                },
                enabled = isFormValid(),
                isLoading = state.isLoading
            )
            
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
                Text("Sign In Instead")
            }
            
            Spacer(modifier = Modifier.height(32.dp))
        }
        
        // Error snackbar
        AnimatedVisibility(
            visible = state.error != null && 
                     !state.error.contains("email", ignoreCase = true) && 
                     !state.error.contains("password", ignoreCase = true) &&
                     !state.error.contains("name", ignoreCase = true) &&
                     !state.error.contains("phone", ignoreCase = true) &&
                     !state.error.contains("location", ignoreCase = true),
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

@Composable
private fun isFormValid(): Boolean {
    return email.isNotBlank() &&
           password.isNotBlank() &&
           confirmPassword.isNotBlank() &&
           fullName.isNotBlank() &&
           phoneNumber.isNotBlank() &&
           selectedRegionId.isNotBlank() &&
           selectedDistrictId.isNotBlank() &&
           selectedLocationId.isNotBlank() &&
           password.length >= 8 &&
           password == confirmPassword &&
           phoneNumberError == null
} 