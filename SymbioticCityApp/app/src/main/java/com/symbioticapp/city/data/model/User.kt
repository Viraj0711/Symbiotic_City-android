package com.symbioticapp.city.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val id: String? = null,
    
    @SerializedName("name")
    val name: String,
    
    @SerializedName("email")
    val email: String,
    
    @SerializedName("avatar")
    val avatar: String? = null,
    
    @SerializedName("bio")
    val bio: String? = null,
    
    @SerializedName("location")
    val location: String? = null,
    
    @SerializedName("role")
    val role: String = "USER",
    
    @SerializedName("isActive")
    val isActive: Boolean = true,
    
    @SerializedName("emailVerified")
    val emailVerified: Boolean = false,
    
    @SerializedName("createdAt")
    val createdAt: String? = null,
    
    @SerializedName("updatedAt")
    val updatedAt: String? = null
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)

data class AuthResponse(
    @SerializedName("token")
    val token: String,
    
    @SerializedName("user")
    val user: User
)

data class ApiResponse<T>(
    @SerializedName("success")
    val success: Boolean,
    
    @SerializedName("data")
    val data: T?,
    
    @SerializedName("message")
    val message: String? = null
)
