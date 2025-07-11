package org.farmetricsapp.data.remote.models

sealed class ApiResponse<out T> {
    data class Success<T>(val data: T) : ApiResponse<T>()
    data class Error(val message: String, val code: Int? = null) : ApiResponse<Nothing>()
    data object Loading : ApiResponse<Nothing>()

    companion object {
        fun <T> success(data: T) = Success(data)
        fun error(message: String, code: Int? = null) = Error(message, code)
        fun loading() = Loading
    }
} 