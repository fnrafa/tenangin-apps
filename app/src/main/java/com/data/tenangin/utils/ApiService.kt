package com.data.tenangin.utils

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

data class ApiResponse<T>(
    val status: String,
    val message: String,
    val data: T? = null
)

data class RegisterRequest(
    val email: String,
    val username: String,
    val name: String,
    val password: String,
    val repeat_password: String
)

data class LoginRequest(
    val username: String,
    val password: String
)


interface ApiService {
    @GET("/")
    suspend fun pingServer(): Response<ApiResponse<Unit>>

    @POST("/auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<ApiResponse<Unit>>

    @POST("/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<ApiResponse<Unit>>
}
