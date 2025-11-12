package com.symbioticapp.city.data.api

import com.symbioticapp.city.data.model.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    
    // Auth endpoints
    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<AuthResponse>
    
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<AuthResponse>
    
    @GET("auth/me")
    suspend fun getCurrentUser(@Header("Authorization") token: String): Response<ApiResponse<User>>
    
    @PUT("auth/profile")
    suspend fun updateProfile(
        @Header("Authorization") token: String,
        @Body user: User
    ): Response<ApiResponse<User>>
    
    // Projects endpoints
    @GET("projects")
    suspend fun getProjects(@Header("Authorization") token: String): Response<ApiResponse<List<Project>>>
    
    @GET("projects/{id}")
    suspend fun getProject(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Response<ApiResponse<Project>>
    
    @POST("projects")
    suspend fun createProject(
        @Header("Authorization") token: String,
        @Body project: Project
    ): Response<ApiResponse<Project>>
    
    @PUT("projects/{id}")
    suspend fun updateProject(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Body project: Project
    ): Response<ApiResponse<Project>>
    
    @DELETE("projects/{id}")
    suspend fun deleteProject(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Response<ApiResponse<Unit>>
    
    // Events endpoints
    @GET("events")
    suspend fun getEvents(@Header("Authorization") token: String): Response<ApiResponse<List<Event>>>
    
    @GET("events/{id}")
    suspend fun getEvent(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Response<ApiResponse<Event>>
    
    @POST("events")
    suspend fun createEvent(
        @Header("Authorization") token: String,
        @Body event: Event
    ): Response<ApiResponse<Event>>
    
    // Marketplace endpoints
    @GET("marketplace")
    suspend fun getMarketplaceItems(@Header("Authorization") token: String): Response<ApiResponse<List<MarketplaceItem>>>
    
    @GET("marketplace/{id}")
    suspend fun getMarketplaceItem(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Response<ApiResponse<MarketplaceItem>>
    
    @POST("marketplace")
    suspend fun createMarketplaceItem(
        @Header("Authorization") token: String,
        @Body item: MarketplaceItem
    ): Response<ApiResponse<MarketplaceItem>>
}
