
package com.unlam.edutechapp

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.unlam.edutechapp.services.EduTechService

class MyCoursesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_courses)

        val contenedor = findViewById<LinearLayout>(
            R.id.contenedorMisCursos
        )

        val cursos = EduTechService.getMyCourses()

        if (cursos.isEmpty()) {

            val mensaje = TextView(this)
            mensaje.text = "Todavía no estás inscripto en ningún curso"
            contenedor.addView(mensaje)

        } else {

            cursos.forEach { detalle ->

                val curso = detalle.course
                val inscripcion = detalle.enrolled

                val textoCurso = TextView(this)

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
