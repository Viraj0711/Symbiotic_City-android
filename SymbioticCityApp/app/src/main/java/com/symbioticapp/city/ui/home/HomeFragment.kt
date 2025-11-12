package com.symbioticapp.city.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.symbioticapp.city.R
import com.symbioticapp.city.util.TokenManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    
    private lateinit var tvWelcome: TextView
    private lateinit var tokenManager: TokenManager
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        tokenManager = TokenManager(requireContext())
        tvWelcome = view.findViewById(R.id.tvWelcome)
        
        loadUserInfo()
    }
    
    private fun loadUserInfo() {
        lifecycleScope.launch {
            val userName = tokenManager.getUserName().first()
            tvWelcome.text = "Welcome back, ${userName ?: "User"}!"
        }
    }
}
