package com.symbioticapp.city.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.button.MaterialButton
import com.symbioticapp.city.R
import com.symbioticapp.city.ui.auth.LoginActivity
import com.symbioticapp.city.util.TokenManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {
    
    private lateinit var tokenManager: TokenManager
    private lateinit var tvUserName: TextView
    private lateinit var tvUserEmail: TextView
    private lateinit var btnLogout: MaterialButton
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        tokenManager = TokenManager(requireContext())
        
        initViews(view)
        loadUserInfo()
        setupClickListeners()
    }
    
    private fun initViews(view: View) {
        tvUserName = view.findViewById(R.id.tvUserName)
        tvUserEmail = view.findViewById(R.id.tvUserEmail)
        btnLogout = view.findViewById(R.id.btnLogout)
    }
    
    private fun loadUserInfo() {
        lifecycleScope.launch {
            val userName = tokenManager.getUserName().first()
            val userEmail = tokenManager.getUserEmail().first()
            
            tvUserName.text = userName ?: "User"
            tvUserEmail.text = userEmail ?: "email@example.com"
        }
    }
    
    private fun setupClickListeners() {
        view?.findViewById<View>(R.id.cardEditProfile)?.setOnClickListener {
            Toast.makeText(context, "Edit Profile - Coming soon!", Toast.LENGTH_SHORT).show()
        }
        
        view?.findViewById<View>(R.id.cardSettings)?.setOnClickListener {
            Toast.makeText(context, "Settings - Coming soon!", Toast.LENGTH_SHORT).show()
        }
        
        view?.findViewById<View>(R.id.cardAbout)?.setOnClickListener {
            Toast.makeText(context, "Symbiotic City v1.0", Toast.LENGTH_SHORT).show()
        }
        
        btnLogout.setOnClickListener {
            performLogout()
        }
    }
    
    private fun performLogout() {
        lifecycleScope.launch {
            tokenManager.clearAll()
            
            val intent = Intent(requireContext(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            requireActivity().finish()
        }
    }
}
