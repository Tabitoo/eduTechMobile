package com.unlam.edutechapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btnDisponibles = findViewById<Button>(
            R.id.btnCursosDisponibles
        )

        val btnMisCursos = findViewById<Button>(
            R.id.btnMisCursos
        )

        btnDisponibles.setOnClickListener {
            val intent = Intent(this, AvailableCoursesActivity::class.java)
            startActivity(intent)
        }

        btnMisCursos.setOnClickListener {
            val intent = Intent(this, MyCoursesActivity::class.java)
            startActivity(intent)
        }
    }
}
