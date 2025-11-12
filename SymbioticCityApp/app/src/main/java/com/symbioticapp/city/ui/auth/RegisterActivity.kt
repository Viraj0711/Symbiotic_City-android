package com.symbioticapp.city.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.symbioticapp.city.MainActivity
import com.symbioticapp.city.R
import com.symbioticapp.city.util.Resource

class RegisterActivity : AppCompatActivity() {
    
    private lateinit var viewModel: AuthViewModel
    private lateinit var etName: TextInputEditText
    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var btnRegister: MaterialButton
    private lateinit var progressBar: View
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        
        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        
        initViews()
        setupObservers()
        setupClickListeners()
    }
    
    private fun initViews() {
        etName = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnRegister = findViewById(R.id.btnRegister)
        progressBar = findViewById(R.id.progressBar)
    }
    
    private fun setupObservers() {
        viewModel.registerResult.observe(this) { result ->
            when (result) {
                is Resource.Loading -> {
                    showLoading(true)
                }
                is Resource.Success -> {
                    showLoading(false)
                    Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show()
                    navigateToMain()
                }
                is Resource.Error -> {
                    showLoading(false)
                    Toast.makeText(this, result.message ?: "Registration failed", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    
    private fun setupClickListeners() {
        btnRegister.setOnClickListener {
            val name = etName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            
            if (validateInput(name, email, password)) {
                viewModel.register(name, email, password)
            }
        }
        
        findViewById<View>(R.id.tvLoginLink).setOnClickListener {
            finish()
        }
    }
    
    private fun validateInput(name: String, email: String, password: String): Boolean {
        if (name.isEmpty()) {
            etName.error = "Name is required"
            return false
        }
        if (email.isEmpty()) {
            etEmail.error = "Email is required"
            return false
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.error = "Invalid email format"
            return false
        }
        if (password.isEmpty()) {
            etPassword.error = "Password is required"
            return false
        }
        if (password.length < 6) {
            etPassword.error = "Password must be at least 6 characters"
            return false
        }
        return true
    }
    
    private fun showLoading(isLoading: Boolean) {
        btnRegister.isEnabled = !isLoading
        progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
    
    private fun navigateToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
