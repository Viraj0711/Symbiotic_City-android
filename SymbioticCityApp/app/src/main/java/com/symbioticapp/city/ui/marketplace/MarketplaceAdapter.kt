package com.symbioticapp.city.ui.marketplace

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.symbioticapp.city.R
import com.symbioticapp.city.data.model.MarketplaceItem

class MarketplaceAdapter(
    private var items: List<MarketplaceItem> = emptyList(),
    private val onItemClick: (MarketplaceItem) -> Unit
) : RecyclerView.Adapter<MarketplaceAdapter.MarketplaceViewHolder>() {

    fun updateItems(newItems: List<MarketplaceItem>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarketplaceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_marketplace, parent, false)
        return MarketplaceViewHolder(view)
    }

    override fun onBindViewHolder(holder: MarketplaceViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class MarketplaceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        private val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
        private val tvStatus: TextView = itemView.findViewById(R.id.tvStatus)
        private val tvCategory: TextView = itemView.findViewById(R.id.tvCategory)

        fun bind(item: MarketplaceItem) {
            tvTitle.text = item.title
            tvDescription.text = item.description
            tvPrice.text = "$${item.price ?: 0.0}"
            tvStatus.text = item.status
            tvCategory.text = item.category
            
            itemView.setOnClickListener {
                onItemClick(item)
            }
        }
    }
}
