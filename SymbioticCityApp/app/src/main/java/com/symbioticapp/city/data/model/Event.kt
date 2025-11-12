package com.symbioticapp.city.data.model

import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("id")
    val id: String? = null,
    
    @SerializedName("title")
    val title: String,
    
    @SerializedName("description")
    val description: String,
    
    @SerializedName("startDate")
    val startDate: String,
    
    @SerializedName("endDate")
    val endDate: String? = null,
    
    @SerializedName("location")
    val location: Location? = null,
    
    @SerializedName("category")
    val category: String,
    
    @SerializedName("tags")
    val tags: List<String> = emptyList(),
    
    @SerializedName("organizerId")
    val organizerId: String,
    
    @SerializedName("organizer")
    val organizer: User? = null,
    
    @SerializedName("attendees")
    val attendees: List<String> = emptyList(),
    
    @SerializedName("maxAttendees")
    val maxAttendees: Int? = null,
    
    @SerializedName("isVirtual")
    val isVirtual: Boolean = false,
    
    @SerializedName("virtualLink")
    val virtualLink: String? = null,
    
    @SerializedName("createdAt")
    val createdAt: String? = null,
    
    @SerializedName("updatedAt")
    val updatedAt: String? = null
)
