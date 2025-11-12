package com.symbioticapp.city

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.symbioticapp.city.ui.auth.LoginActivity
import com.symbioticapp.city.util.TokenManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        checkAuthStatus()
    }
    
    private fun checkAuthStatus() {
        val tokenManager = TokenManager(this)
        
        lifecycleScope.launch {
            val token = tokenManager.getToken().first()
            
            if (token != null) {
                // User is logged in
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            } else {
                // User is not logged in
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            }
            finish()
        }
    }
}
