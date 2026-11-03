package com.unlam.edutechapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.unlam.edutechapp.services.EduTechService

class AvailableCoursesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_available_courses)

        val contenedor = findViewById<android.widget.LinearLayout>(
            R.id.contenedorCursos
        )

        val cursos = EduTechService.getAvailableCourses()

        if (cursos.isEmpty()) {

            val mensaje = TextView(this)
            mensaje.text = "No hay cursos disponibles"
            contenedor.addView(mensaje)

        } else {

            cursos.forEach { curso ->

                val textoCurso = TextView(this)

                textoCurso.text = """
                    ${curso.name}
                    Profesor: ${curso.instructor}
                    Fecha: ${curso.date}
                    Horario: ${curso.time}
                    Ubicación: ${curso.location}
                    Cupos: ${curso.capacity}
                    
                """.trimIndent()

                textoCurso.textSize = 18f
                textoCurso.setPadding(12, 20, 12, 20)

                contenedor.addView(textoCurso)
            }
        }
    }
}

