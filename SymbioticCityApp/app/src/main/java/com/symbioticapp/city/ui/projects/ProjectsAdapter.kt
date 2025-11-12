package com.symbioticapp.city.ui.projects

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.symbioticapp.city.R
import com.symbioticapp.city.data.model.Project
import java.text.SimpleDateFormat
import java.util.*

class ProjectsAdapter(
    private var projects: List<Project> = emptyList(),
    private val onProjectClick: (Project) -> Unit
) : RecyclerView.Adapter<ProjectsAdapter.ProjectViewHolder>() {

    fun updateProjects(newProjects: List<Project>) {
        projects = newProjects
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_project, parent, false)
        return ProjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.bind(projects[position])
    }

    override fun getItemCount(): Int = projects.size

    inner class ProjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        private val tvStatus: TextView = itemView.findViewById(R.id.tvStatus)
        private val tvCategory: TextView = itemView.findViewById(R.id.tvCategory)
        private val tvTags: TextView = itemView.findViewById(R.id.tvTags)
        private val tvParticipants: TextView = itemView.findViewById(R.id.tvParticipants)
        private val tvDate: TextView = itemView.findViewById(R.id.tvDate)

        fun bind(project: Project) {
            tvTitle.text = project.title
            tvDescription.text = project.description
            tvStatus.text = project.status
            tvCategory.text = project.category
            
            // Format tags
            if (project.tags.isNotEmpty()) {
                tvTags.text = project.tags.joinToString(" ") { "#$it" }
                tvTags.visibility = View.VISIBLE
            } else {
                tvTags.visibility = View.GONE
            }
            
            // Participants count
            tvParticipants.text = "👥 ${project.participants.size} participants"
            
            // Format date
            tvDate.text = formatDate(project.createdAt)
            
            itemView.setOnClickListener {
                onProjectClick(project)
            }
        }
        
        private fun formatDate(dateString: String?): String {
            if (dateString == null) return "📅 Recently"
            return try {
                val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
                val formatter = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
                val date = parser.parse(dateString)
                "📅 ${date?.let { formatter.format(it) } ?: "Recently"}"
            } catch (e: Exception) {
                "📅 Recently"
            }
        }
    }
}
