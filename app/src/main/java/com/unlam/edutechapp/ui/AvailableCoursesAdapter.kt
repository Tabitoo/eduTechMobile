package com.unlam.edutechapp.ui
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.unlam.edutechapp.data.Course
import com.unlam.edutechapp.R

class AvailableCoursesAdapter(val courses: List<Course>, val onCourseClick: (Course) -> Unit) :  RecyclerView.Adapter<AvailableCoursesAdapter.CourseAvailableViewHolder>() {

    class CourseAvailableViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val itemCourse: TextView = itemView.findViewById(R.id.tvTitle)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CourseAvailableViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_available_course, parent, false)
        return CourseAvailableViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(
        holder: CourseAvailableViewHolder,
        position: Int,
    ) {
        val course = courses[position]
        holder.itemCourse.text = """
                    ${course.name}
                    Profesor: ${course.instructor}
                    Fecha: ${course.date}
                    Horario: ${course.time}
                    Ubicación: ${course.location}
                    Cupos: ${course.capacity}
                    
                """

        holder.itemCourse.setOnClickListener{
            onCourseClick(course)
        }

    }

    override fun getItemCount(): Int = courses.size
}