package org.farmetricsapp.data.repository

import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.postgrest.query.filter.FilterOperation
import io.github.jan.supabase.postgrest.query.filter.FilterOperator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import org.farmetricsapp.domain.model.AuthResult
import org.farmetricsapp.domain.model.SignInCredentials
import org.farmetricsapp.domain.model.SignUpData
import org.farmetricsapp.domain.repository.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val supabaseClient: io.github.jan.supabase.SupabaseClient
) : AuthRepository {
    
    private val auth: Auth get() = supabaseClient.auth
    private val postgrest: Postgrest get() = supabaseClient.postgrest
    
    override suspend fun signIn(credentials: SignInCredentials): AuthResult = withContext(Dispatchers.IO) {
        try {
            val response = auth.signInWith(Email) {
                email = credentials.email
                password = credentials.password
            }
            
            // Get the field officer ID
            val officerId = getCurrentOfficerId()
            
            if (officerId != null) {
                AuthResult(success = true, officerId = officerId)
            } else {
                AuthResult(success = false, error = "Field officer record not found")
            }
        } catch (e: Exception) {
            AuthResult(success = false, error = e.message)
        }
    }
    
    override suspend fun signUp(data: SignUpData): AuthResult = withContext(Dispatchers.IO) {
        try {
            // Create auth user
            val response = auth.signUpWith(Email) {
                email = data.email
                password = data.password
            }
            
            // Create field officer record
            val fieldOfficer = postgrest["field_officers"]
                .insert(
                    values = mapOf(
                        "user_id" to response.user?.id,
                        "full_name" to data.fullName,
                        "email" to data.email,
                        "phone_number" to data.phoneNumber,
                        "region_id" to data.regionId,
                        "district_id" to data.districtId,
                        "location_id" to data.locationId
                    )
                )
                .select(columns = Columns.list("officer_id"))
                .decodeSingle<FieldOfficerResponse>()
            
            AuthResult(success = true, officerId = fieldOfficer.officerId)
        } catch (e: Exception) {
            AuthResult(success = false, error = e.message)
        }
    }
    
    override suspend fun resetPassword(email: String): AuthResult = withContext(Dispatchers.IO) {
        try {
            auth.resetPasswordForEmail(email)
            AuthResult(success = true)
        } catch (e: Exception) {
            AuthResult(success = false, error = e.message)
        }
    }
    
    override suspend fun signOut() = withContext(Dispatchers.IO) {
        auth.signOut()
    }
    
    override suspend fun getCurrentOfficerId(): String? = withContext(Dispatchers.IO) {
        try {
            val userId = auth.currentUserOrNull()?.id ?: return@withContext null
            
            val fieldOfficer = postgrest["field_officers"]
                .select(columns = Columns.list("officer_id")) {
                    filter {
                        eq("user_id", userId)
                    }
                }
                .decodeSingleOrNull<FieldOfficerResponse>()
            
            fieldOfficer?.officerId
        } catch (e: Exception) {
            null
        }
    }
}

@Serializable
private data class FieldOfficerResponse(
    val officerId: String
) 