package com.symbioticapp.city.ui.events

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.symbioticapp.city.data.model.Event
import com.symbioticapp.city.data.repository.EventRepository
import com.symbioticapp.city.util.Resource
import com.symbioticapp.city.util.TokenManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EventsViewModel(application: Application) : AndroidViewModel(application) {
    
    private val repository = EventRepository()
    private val tokenManager = TokenManager(application)
    
    private val _events = MutableLiveData<Resource<List<Event>>>()
    val events: LiveData<Resource<List<Event>>> = _events
    
    fun loadEvents() {
        _events.value = Resource.Loading()
        viewModelScope.launch {
            val token = tokenManager.getToken().first()
            if (token != null) {
                val result = repository.getEvents(token)
                _events.value = result
            } else {
                _events.value = Resource.Error("Not authenticated")
            }
        }
    }
}
