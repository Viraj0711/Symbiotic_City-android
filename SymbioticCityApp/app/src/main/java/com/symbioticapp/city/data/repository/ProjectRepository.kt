package com.symbioticapp.city.data.repository

import com.symbioticapp.city.data.api.RetrofitClient
import com.symbioticapp.city.data.model.Project
import com.symbioticapp.city.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProjectRepository {
    
    private val apiService = RetrofitClient.apiService
    
    suspend fun getProjects(token: String): Resource<List<Project>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getProjects("Bearer $token")
                if (response.isSuccessful && response.body() != null) {
                    val projects = response.body()?.data ?: emptyList()
                    Resource.Success(projects)
                } else {
                    Resource.Error("Failed to load projects: ${response.message()}")
                }
            } catch (e: Exception) {
                Resource.Error("Network error: ${e.localizedMessage}")
            }
        }
    }
    
    suspend fun getProject(token: String, projectId: String): Resource<Project> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getProject("Bearer $token", projectId)
                if (response.isSuccessful && response.body()?.data != null) {
                    Resource.Success(response.body()!!.data!!)
                } else {
                    Resource.Error("Failed to load project: ${response.message()}")
                }
            } catch (e: Exception) {
                Resource.Error("Network error: ${e.localizedMessage}")
            }
        }
    }
    
    suspend fun createProject(token: String, project: Project): Resource<Project> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.createProject("Bearer $token", project)
                if (response.isSuccessful && response.body()?.data != null) {
                    Resource.Success(response.body()!!.data!!)
                } else {
                    Resource.Error("Failed to create project: ${response.message()}")
                }
            } catch (e: Exception) {
                Resource.Error("Network error: ${e.localizedMessage}")
            }
        }
    }
}
