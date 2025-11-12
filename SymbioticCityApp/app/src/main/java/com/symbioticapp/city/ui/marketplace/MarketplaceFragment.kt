package com.symbioticapp.city.ui.marketplace

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.symbioticapp.city.R
import com.symbioticapp.city.util.Resource

class MarketplaceFragment : Fragment() {
    
    private lateinit var viewModel: MarketplaceViewModel
    private lateinit var adapter: MarketplaceAdapter
    private lateinit var rvMarketplace: RecyclerView
    private lateinit var progressBar: View
    private lateinit var layoutEmpty: View
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_marketplace, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        viewModel = ViewModelProvider(this)[MarketplaceViewModel::class.java]
        
        initViews(view)
        setupRecyclerView()
        setupObservers()
        
        viewModel.loadItems()
    }
    
    private fun initViews(view: View) {
        rvMarketplace = view.findViewById(R.id.rvMarketplace)
        progressBar = view.findViewById(R.id.progressBar)
        layoutEmpty = view.findViewById(R.id.layoutEmpty)
    }
    
    private fun setupRecyclerView() {
        adapter = MarketplaceAdapter { item ->
            Toast.makeText(context, "Item: ${item.title}", Toast.LENGTH_SHORT).show()
        }
        
        rvMarketplace.layoutManager = GridLayoutManager(context, 2)
        rvMarketplace.adapter = adapter
    }
    
    private fun setupObservers() {
        viewModel.items.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Loading -> showLoading(true)
                is Resource.Success -> {
                    showLoading(false)
                    result.data?.let { items ->
                        if (items.isEmpty()) {
                            showEmptyState(true)
                        } else {
                            showEmptyState(false)
                            adapter.updateItems(items)
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
        rvMarketplace.visibility = if (isLoading) View.GONE else View.VISIBLE
    }
    
    private fun showEmptyState(isEmpty: Boolean) {
        layoutEmpty.visibility = if (isEmpty) View.VISIBLE else View.GONE
        rvMarketplace.visibility = if (isEmpty) View.GONE else View.VISIBLE
    }
}
