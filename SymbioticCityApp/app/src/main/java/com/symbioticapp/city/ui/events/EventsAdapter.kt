package com.symbioticapp.city.ui.events

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.symbioticapp.city.R
import com.symbioticapp.city.data.model.Event
import java.text.SimpleDateFormat
import java.util.*

class EventsAdapter(
    private var events: List<Event> = emptyList(),
    private val onEventClick: (Event) -> Unit
) : RecyclerView.Adapter<EventsAdapter.EventViewHolder>() {

    fun updateEvents(newEvents: List<Event>) {
        events = newEvents
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_event, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(events[position])
    }

    override fun getItemCount(): Int = events.size

    inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        private val tvDate: TextView = itemView.findViewById(R.id.tvDate)
        private val tvAttendees: TextView = itemView.findViewById(R.id.tvAttendees)
        private val tvCategory: TextView = itemView.findViewById(R.id.tvCategory)

        fun bind(event: Event) {
            tvTitle.text = event.title
            tvDescription.text = event.description
            tvCategory.text = event.category
            tvAttendees.text = "👥 ${event.attendees.size} attending"
            tvDate.text = formatDate(event.startDate)
            
            itemView.setOnClickListener {
                onEventClick(event)
            }
        }
        
        private fun formatDate(dateString: String): String {
            return try {
                val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
                val formatter = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
                val date = parser.parse(dateString)
                "📅 ${date?.let { formatter.format(it) } ?: "TBD"}"
            } catch (e: Exception) {
                "📅 $dateString"
            }
        }
    }
}
