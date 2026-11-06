package com.unlam.edutechapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.unlam.edutechapp.AvailableCoursesActivity
import com.unlam.edutechapp.MyCoursesActivity
import com.unlam.edutechapp.R

class MenuFragment: Fragment(R.layout.menu_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnDisponibles = view.findViewById<Button>(
            R.id.btnCursosDisponibles
        )

        val btnMisCursos = view.findViewById<Button>(
            R.id.btnMisCursos
        )

        btnDisponibles.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_availableCoursesFragment)
        }

        btnMisCursos.setOnClickListener {
            findNavController().navigate((R.id.action_menuFragment_to_myCoursesFragment))
        }

    }
}