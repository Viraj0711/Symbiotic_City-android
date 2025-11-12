package com.symbioticapp.city.data.repository

import com.symbioticapp.city.data.api.RetrofitClient
import com.symbioticapp.city.data.model.LoginRequest
import com.symbioticapp.city.data.model.RegisterRequest
import com.symbioticapp.city.data.model.AuthResponse
import com.symbioticapp.city.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepository {
    
    private val apiService = RetrofitClient.apiService
    
    suspend fun login(email: String, password: String): Resource<AuthResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.login(LoginRequest(email, password))
                if (response.isSuccessful && response.body() != null) {
                    Resource.Success(response.body()!!)
                } else {
                    Resource.Error("Login failed: ${response.message()}")
                }
            } catch (e: Exception) {
                Resource.Error("Network error: ${e.localizedMessage}")
            }
        }
    }
    
    suspend fun register(name: String, email: String, password: String): Resource<AuthResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.register(RegisterRequest(name, email, password))
                if (response.isSuccessful && response.body() != null) {
                    Resource.Success(response.body()!!)
                } else {
                    Resource.Error("Registration failed: ${response.message()}")
                }
            } catch (e: Exception) {
                Resource.Error("Network error: ${e.localizedMessage}")
            }
        }
    }
}
