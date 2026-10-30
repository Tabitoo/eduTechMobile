package com.unlam.edutechapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.unlam.edutechapp.services.EduTechService

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val usuario = findViewById<EditText>(R.id.etUsuario)
        val password = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {

            val nickname = usuario.text.toString()
            val contrasena = password.text.toString()

            val loginCorrecto = EduTechService.login(
                nickname,
                contrasena
            )

            if (loginCorrecto) {

                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
                finish()

            } else {

                Toast.makeText(
                    this,
                    "Usuario o contraseña incorrectos",
                    Toast.LENGTH_SHORT
                ).show()

            }
        }
    }
}
