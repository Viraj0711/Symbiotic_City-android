package com.symbioticapp.city.ui.marketplace

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.symbioticapp.city.data.model.MarketplaceItem
import com.symbioticapp.city.data.repository.MarketplaceRepository
import com.symbioticapp.city.util.Resource
import com.symbioticapp.city.util.TokenManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MarketplaceViewModel(application: Application) : AndroidViewModel(application) {
    
    private val repository = MarketplaceRepository()
    private val tokenManager = TokenManager(application)
    
    private val _items = MutableLiveData<Resource<List<MarketplaceItem>>>()
    val items: LiveData<Resource<List<MarketplaceItem>>> = _items
    
    fun loadItems() {
        _items.value = Resource.Loading()
        viewModelScope.launch {
            val token = tokenManager.getToken().first()
            if (token != null) {
                val result = repository.getMarketplaceItems(token)
                _items.value = result
            } else {
                _items.value = Resource.Error("Not authenticated")
            }
        }
    }
}
