package com.symbioticapp.city

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.symbioticapp.city.ui.events.EventsFragment
import com.symbioticapp.city.ui.home.HomeFragment
import com.symbioticapp.city.ui.marketplace.MarketplaceFragment
import com.symbioticapp.city.ui.profile.ProfileFragment
import com.symbioticapp.city.ui.projects.ProjectsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        
        // Load Home fragment by default
        loadFragment(HomeFragment())
        
        setupBottomNavigation()
    }
    
    private fun setupBottomNavigation() {
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.nav_projects -> {
                    loadFragment(ProjectsFragment())
                    true
                }
                R.id.nav_events -> {
                    loadFragment(EventsFragment())
                    true
                }
                R.id.nav_marketplace -> {
                    loadFragment(MarketplaceFragment())
                    true
                }
                R.id.nav_profile -> {
                    loadFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }
    }
    
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .commit()
    }
}
