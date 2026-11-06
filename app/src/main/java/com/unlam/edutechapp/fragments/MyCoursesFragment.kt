package com.unlam.edutechapp.fragments

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.unlam.edutechapp.R
import com.unlam.edutechapp.services.EduTechService

class MyCoursesFragment: Fragment(R.layout.my_courses_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val contenedor = view.findViewById<LinearLayout>(
            R.id.contenedorMisCursos
        )

        val cursos = EduTechService.getMyCourses()

        if (cursos.isEmpty()) {

            val mensaje = TextView(requireContext())
            mensaje.text = "Todavía no estás inscripto en ningún curso"
            contenedor.addView(mensaje)

        } else {

            cursos.forEach { detalle ->

                val curso = detalle.course
                val inscripcion = detalle.enrolled

                val textoCurso = TextView(requireContext())

                textoCurso.text = """
                    ${curso.name}
                    Profesor: ${curso.instructor}
                    Fecha: ${curso.date}
                    Horario: ${curso.time}
                    Modalidad: ${inscripcion.type}
                    Duración: ${inscripcion.duration} horas
                    Estado: ${if (inscripcion.completed) "Completado" else "En curso"}
                    
                """.trimIndent()

                textoCurso.textSize = 18f
                textoCurso.setPadding(12, 20, 12, 20)

                contenedor.addView(textoCurso)
            }
        }
    }


}