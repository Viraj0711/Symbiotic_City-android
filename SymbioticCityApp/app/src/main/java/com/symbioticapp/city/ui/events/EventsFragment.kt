package com.symbioticapp.city.ui.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.symbioticapp.city.R
import com.symbioticapp.city.util.Resource

class EventsFragment : Fragment() {
    
    private lateinit var viewModel: EventsViewModel
    private lateinit var adapter: EventsAdapter
    private lateinit var rvEvents: RecyclerView
    private lateinit var progressBar: View
    private lateinit var layoutEmpty: View
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_events, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        viewModel = ViewModelProvider(this)[EventsViewModel::class.java]
        
        initViews(view)
        setupRecyclerView()
        setupObservers()
        
        viewModel.loadEvents()
    }
    
    private fun initViews(view: View) {
        rvEvents = view.findViewById(R.id.rvEvents)
        progressBar = view.findViewById(R.id.progressBar)
        layoutEmpty = view.findViewById(R.id.layoutEmpty)
    }
    
    private fun setupRecyclerView() {
        adapter = EventsAdapter { event ->
            Toast.makeText(context, "Event: ${event.title}", Toast.LENGTH_SHORT).show()
        }
        
        rvEvents.layoutManager = LinearLayoutManager(context)
        rvEvents.adapter = adapter
    }
    
    private fun setupObservers() {
        viewModel.events.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Loading -> showLoading(true)
                is Resource.Success -> {
                    showLoading(false)
                    result.data?.let { events ->
                        if (events.isEmpty()) {
                            showEmptyState(true)
                        } else {
                            showEmptyState(false)
                            adapter.updateEvents(events)
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
    
    private fun showLoading(isLoading: Boolean) {
        progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        rvEvents.visibility = if (isLoading) View.GONE else View.VISIBLE
    }
    
    private fun showEmptyState(isEmpty: Boolean) {
        layoutEmpty.visibility = if (isEmpty) View.VISIBLE else View.GONE
        rvEvents.visibility = if (isEmpty) View.GONE else View.VISIBLE
    }
}
