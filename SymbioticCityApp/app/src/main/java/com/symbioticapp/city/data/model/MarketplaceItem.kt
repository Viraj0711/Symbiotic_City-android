package com.symbioticapp.city.data.model

import com.google.gson.annotations.SerializedName

data class MarketplaceItem(
    @SerializedName("id")
    val id: String? = null,
    
    @SerializedName("title")
    val title: String,
    
    @SerializedName("description")
    val description: String,
    
    @SerializedName("category")
    val category: String,
    
    @SerializedName("price")
    val price: Double? = null,
    
    @SerializedName("currency")
    val currency: String = "USD",
    
    @SerializedName("condition")
    val condition: String? = null, // New, Used, etc.
    
    @SerializedName("images")
    val images: List<String> = emptyList(),
    
    @SerializedName("sellerId")
    val sellerId: String,
    
    @SerializedName("seller")
    val seller: User? = null,
    
    @SerializedName("location")
    val location: Location? = null,
    
    @SerializedName("status")
    val status: String = "AVAILABLE", // AVAILABLE, SOLD, RESERVED
    
    @SerializedName("tags")
    val tags: List<String> = emptyList(),
    
    @SerializedName("createdAt")
    val createdAt: String? = null,
    
    @SerializedName("updatedAt")
    val updatedAt: String? = null
)
