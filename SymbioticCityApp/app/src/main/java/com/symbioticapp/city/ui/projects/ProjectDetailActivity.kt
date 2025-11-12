package com.symbioticapp.city.ui.projects

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.symbioticapp.city.R
import com.symbioticapp.city.data.model.Project

class ProjectDetailActivity : AppCompatActivity() {
    
    private lateinit var tvProjectTitle: TextView
    private lateinit var tvProjectDescription: TextView
    private lateinit var tvLocation: TextView
    private lateinit var tvParticipantsCount: TextView
    private lateinit var chipStatus: Chip
    private lateinit var chipCategory: Chip
    private lateinit var chipGroupTags: ChipGroup
    private lateinit var btnJoinProject: MaterialButton
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_detail)
        
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Project Details"
        
        initViews()
        loadProjectData()
        setupClickListeners()
    }
    
    private fun initViews() {
        tvProjectTitle = findViewById(R.id.tvProjectTitle)
        tvProjectDescription = findViewById(R.id.tvProjectDescription)
        tvLocation = findViewById(R.id.tvLocation)
        tvParticipantsCount = findViewById(R.id.tvParticipantsCount)
        chipStatus = findViewById(R.id.chipStatus)
        chipCategory = findViewById(R.id.chipCategory)
        chipGroupTags = findViewById(R.id.chipGroupTags)
        btnJoinProject = findViewById(R.id.btnJoinProject)
    }
    
    private fun loadProjectData() {
        val projectId = intent.getStringExtra("PROJECT_ID") ?: ""
        val projectTitle = intent.getStringExtra("PROJECT_TITLE") ?: ""
        val projectDescription = intent.getStringExtra("PROJECT_DESCRIPTION") ?: ""
        val projectStatus = intent.getStringExtra("PROJECT_STATUS") ?: "active"
        val projectCategory = intent.getStringExtra("PROJECT_CATEGORY") ?: ""
        val projectLocation = intent.getStringExtra("PROJECT_LOCATION") ?: ""
        val projectTags = intent.getStringArrayListExtra("PROJECT_TAGS") ?: arrayListOf()
        
        tvProjectTitle.text = projectTitle
        tvProjectDescription.text = projectDescription
        tvLocation.text = projectLocation.ifEmpty { "No location specified" }
        chipStatus.text = projectStatus.uppercase()
        chipCategory.text = projectCategory
        
        // Add tags as chips
        projectTags.forEach { tag ->
            val chip = Chip(this)
            chip.text = tag
            chip.isClickable = false
            chipGroupTags.addView(chip)
        }
        
        // Mock participants count
        tvParticipantsCount.text = "5 participants"
    }
    
    private fun setupClickListeners() {
        btnJoinProject.setOnClickListener {
            Toast.makeText(this, "Join Project - Coming soon!", Toast.LENGTH_SHORT).show()
        }
    }
    
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
