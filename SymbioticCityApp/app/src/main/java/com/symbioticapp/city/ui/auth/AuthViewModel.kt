package com.symbioticapp.city.ui.auth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.symbioticapp.city.data.model.AuthResponse
import com.symbioticapp.city.data.repository.AuthRepository
import com.symbioticapp.city.util.Resource
import com.symbioticapp.city.util.TokenManager
import kotlinx.coroutines.launch

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    
    private val repository = AuthRepository()
    private val tokenManager = TokenManager(application)
    
    private val _loginResult = MutableLiveData<Resource<AuthResponse>>()
    val loginResult: LiveData<Resource<AuthResponse>> = _loginResult
    
    private val _registerResult = MutableLiveData<Resource<AuthResponse>>()
    val registerResult: LiveData<Resource<AuthResponse>> = _registerResult
    
    fun login(email: String, password: String) {
        _loginResult.value = Resource.Loading()
        viewModelScope.launch {
            val result = repository.login(email, password)
            _loginResult.value = result
            
            if (result is Resource.Success && result.data != null) {
                tokenManager.saveToken(result.data.token)
                result.data.user.let { user ->
                    user.id?.let { id ->
                        tokenManager.saveUserInfo(id, user.name, user.email)
                    }
                }
            }
        }
    }
    
    fun register(name: String, email: String, password: String) {
        _registerResult.value = Resource.Loading()
        viewModelScope.launch {
            val result = repository.register(name, email, password)
            _registerResult.value = result
            
            if (result is Resource.Success && result.data != null) {
                tokenManager.saveToken(result.data.token)
                result.data.user.let { user ->
                    user.id?.let { id ->
                        tokenManager.saveUserInfo(id, user.name, user.email)
                    }
                }
            }
        }
    }
}
