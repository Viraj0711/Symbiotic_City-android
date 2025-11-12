package com.symbioticapp.city.ui.projects

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.symbioticapp.city.R
import com.symbioticapp.city.util.Resource

class ProjectsFragment : Fragment() {
    
    private lateinit var viewModel: ProjectsViewModel
    private lateinit var adapter: ProjectsAdapter
    private lateinit var rvProjects: RecyclerView
    private lateinit var progressBar: View
    private lateinit var layoutEmpty: View
    private lateinit var fabAdd: FloatingActionButton
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_projects, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        viewModel = ViewModelProvider(this)[ProjectsViewModel::class.java]
        
        initViews(view)
        setupRecyclerView()
        setupObservers()
        setupClickListeners()
        
        // Load projects
        viewModel.loadProjects()
    }
    
    private fun initViews(view: View) {
        rvProjects = view.findViewById(R.id.rvProjects)
        progressBar = view.findViewById(R.id.progressBar)
        layoutEmpty = view.findViewById(R.id.layoutEmpty)
        fabAdd = view.findViewById(R.id.fabAdd)
    }
    
    private fun setupRecyclerView() {
        adapter = ProjectsAdapter { project ->
            // Open project detail activity
            val intent = Intent(requireContext(), ProjectDetailActivity::class.java).apply {
                putExtra("PROJECT_ID", project.id ?: "")
                putExtra("PROJECT_TITLE", project.title)
                putExtra("PROJECT_DESCRIPTION", project.description)
                putExtra("PROJECT_STATUS", project.status)
                putExtra("PROJECT_CATEGORY", project.category)
                putExtra("PROJECT_LOCATION", project.location?.address ?: "")
                putStringArrayListExtra("PROJECT_TAGS", ArrayList(project.tags))
            }
            startActivity(intent)
        }
        
        rvProjects.layoutManager = LinearLayoutManager(context)
        rvProjects.adapter = adapter
    }
    
    private fun setupObservers() {
        viewModel.projects.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Loading -> {
                    showLoading(true)
                }
                is Resource.Success -> {
                    showLoading(false)
                    result.data?.let { projects ->
                        if (projects.isEmpty()) {
                            showEmptyState(true)
                        } else {
                            showEmptyState(false)
                            adapter.updateProjects(projects)
                        }
                    }
                }
                is Resource.Error -> {
                    showLoading(false)
                    Toast.makeText(context, result.message, Toast.LENGTH_LONG).show()
                    showEmptyState(true)
                }
            }
        }
    }
    
    private fun setupClickListeners() {
        fabAdd.setOnClickListener {
            Toast.makeText(context, "Create Project - Coming soon!", Toast.LENGTH_SHORT).show()
        }
    }
    
    private fun showLoading(isLoading: Boolean) {
        progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        rvProjects.visibility = if (isLoading) View.GONE else View.VISIBLE
    }
    
    private fun showEmptyState(isEmpty: Boolean) {
        layoutEmpty.visibility = if (isEmpty) View.VISIBLE else View.GONE
        rvProjects.visibility = if (isEmpty) View.GONE else View.VISIBLE
    }
}
