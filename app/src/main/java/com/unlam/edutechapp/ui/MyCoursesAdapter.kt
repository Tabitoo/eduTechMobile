package com.unlam.edutechapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.unlam.edutechapp.R
import com.unlam.edutechapp.data.CourseDetail

class MyCoursesAdapter(
    private val courses: List<CourseDetail>
) : RecyclerView.Adapter<MyCoursesAdapter.MyCourseViewHolder>() {

    class MyCourseViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val itemCourse: TextView =
            itemView.findViewById(R.id.tvMyCourseItem)

        val courseImage: ImageView =
            itemView.findViewById(R.id.ivMyCourseImage)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyCourseViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_my_course, parent, false)

        return MyCourseViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: MyCourseViewHolder,
        position: Int
    ) {

        val detalle = courses[position]
        val curso = detalle.course
        val inscripcion = detalle.enrolled

        holder.itemCourse.text = """
            ${curso.name}
            Profesor: ${curso.instructor}
            Fecha: ${curso.date}
            Horario: ${curso.time}
            Modalidad: ${inscripcion.type}
            Duración: ${inscripcion.duration} horas
            Estado: ${if (inscripcion.completed) "Completado" else "En curso"}
        """.trimIndent()

        Glide.with(holder.itemView.context)
            .load(curso.image)
            .centerCrop()
            .into(holder.courseImage)
    }

    override fun getItemCount(): Int {
        return courses.size
    }
}
