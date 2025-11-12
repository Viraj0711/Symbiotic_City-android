package com.symbioticapp.city.ui.projects

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.symbioticapp.city.data.model.Project
import com.symbioticapp.city.data.repository.ProjectRepository
import com.symbioticapp.city.util.Resource
import com.symbioticapp.city.util.TokenManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ProjectsViewModel(application: Application) : AndroidViewModel(application) {
    
    private val repository = ProjectRepository()
    private val tokenManager = TokenManager(application)
    
    private val _projects = MutableLiveData<Resource<List<Project>>>()
    val projects: LiveData<Resource<List<Project>>> = _projects
    
    fun loadProjects() {
        _projects.value = Resource.Loading()
        viewModelScope.launch {
            val token = tokenManager.getToken().first()
            if (token != null) {
                val result = repository.getProjects(token)
                _projects.value = result
            } else {
                _projects.value = Resource.Error("Not authenticated")
            }
        }
    }
}
