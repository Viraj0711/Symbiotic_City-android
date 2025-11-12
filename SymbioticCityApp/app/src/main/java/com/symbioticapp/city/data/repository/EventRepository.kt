package com.symbioticapp.city.data.repository

import com.symbioticapp.city.data.api.RetrofitClient
import com.symbioticapp.city.data.model.Event
import com.symbioticapp.city.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EventRepository {
    
    private val apiService = RetrofitClient.apiService
    
    suspend fun getEvents(token: String): Resource<List<Event>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getEvents("Bearer $token")
                if (response.isSuccessful && response.body() != null) {
                    val events = response.body()?.data ?: emptyList()
                    Resource.Success(events)
                } else {
                    Resource.Error("Failed to load events: ${response.message()}")
                }
            } catch (e: Exception) {
                Resource.Error("Network error: ${e.localizedMessage}")
            }
        }
    }
}
