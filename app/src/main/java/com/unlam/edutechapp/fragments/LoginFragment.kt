package com.unlam.edutechapp.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.unlam.edutechapp.R
import com.unlam.edutechapp.services.EduTechService

class LoginFragment: Fragment(R.layout.login_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = view.findViewById<EditText>(R.id.etUsuario)
        val password = view.findViewById<EditText>(R.id.etPassword)
        val btnLogin = view.findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {

            val nickname = user.text.toString()
            val password = password.text.toString()

            val resultLogin = EduTechService.login(
                nickname,
                password
            )

            if (resultLogin) {

                findNavController().navigate(R.id.action_login_to_menu)


            } else {

                Toast.makeText(
                    requireContext(),
                    "Usuario o contraseña incorrectos",
                    Toast.LENGTH_SHORT
                ).show()

            }
        }

    }
}