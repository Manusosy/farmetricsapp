package org.farmetricsapp.data.remote

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.farmetricsapp.data.remote.models.ApiResponse
import java.io.IOException

object NetworkUtils {
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }

    suspend fun <T> safeApiCall(apiCall: suspend () -> T): Flow<ApiResponse<T>> = flow {
        try {
            emit(ApiResponse.Loading)
            val response = apiCall()
            emit(ApiResponse.success(response))
        } catch (e: Exception) {
            when (e) {
                is IOException -> emit(ApiResponse.error("Network Error: ${e.message}"))
                else -> emit(ApiResponse.error("Error: ${e.message}"))
            }
        }
    }

    fun getErrorMessage(error: Throwable): String {
        return when (error) {
            is IOException -> "Network Error: Check your internet connection"
            else -> error.message ?: "Unknown error occurred"
        }
    }
} 