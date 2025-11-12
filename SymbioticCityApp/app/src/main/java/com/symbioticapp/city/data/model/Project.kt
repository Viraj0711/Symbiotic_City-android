package com.symbioticapp.city.data.model

import com.google.gson.annotations.SerializedName

data class Project(
    @SerializedName("id")
    val id: String? = null,
    
    @SerializedName("title")
    val title: String,
    
    @SerializedName("description")
    val description: String,
    
    @SerializedName("status")
    val status: String = "PLANNING", // PLANNING, ACTIVE, COMPLETED, PAUSED
    
    @SerializedName("category")
    val category: String,
    
    @SerializedName("tags")
    val tags: List<String> = emptyList(),
    
    @SerializedName("authorId")
    val authorId: String,
    
    @SerializedName("author")
    val author: User? = null,
    
    @SerializedName("participants")
    val participants: List<String> = emptyList(),
    
    @SerializedName("location")
    val location: Location? = null,
    
    @SerializedName("startDate")
    val startDate: String? = null,
    
    @SerializedName("endDate")
    val endDate: String? = null,
    
    @SerializedName("createdAt")
    val createdAt: String? = null,
    
    @SerializedName("updatedAt")
    val updatedAt: String? = null
)

data class Location(
    @SerializedName("latitude")
    val latitude: Double,
    
    @SerializedName("longitude")
    val longitude: Double,
    
    @SerializedName("address")
    val address: String? = null
)
