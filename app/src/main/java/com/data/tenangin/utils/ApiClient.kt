package com.data.tenangin.utils

import com.google.gson.Gson
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "http://192.168.1.10:3010"

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    fun parseErrorMessage(response: Response<*>): String {
        return try {
            val errorBody = response.errorBody()?.string()
            if (!errorBody.isNullOrEmpty()) {
                val apiError = Gson().fromJson(errorBody, ApiResponse::class.java)
                apiError.message
            } else {
                "An unexpected error occurred"
            }
        } catch (e: Exception) {
            "Failed to parse error response"
        }
    }
}
