package com.symbioticapp.city.data.repository

import com.symbioticapp.city.data.api.RetrofitClient
import com.symbioticapp.city.data.model.MarketplaceItem
import com.symbioticapp.city.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MarketplaceRepository {
    
    private val apiService = RetrofitClient.apiService
    
    suspend fun getMarketplaceItems(token: String): Resource<List<MarketplaceItem>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getMarketplaceItems("Bearer $token")
                if (response.isSuccessful && response.body() != null) {
                    val items = response.body()?.data ?: emptyList()
                    Resource.Success(items)
                } else {
                    Resource.Error("Failed to load items: ${response.message()}")
                }
            } catch (e: Exception) {
                Resource.Error("Network error: ${e.localizedMessage}")
            }
        }
    }
}
