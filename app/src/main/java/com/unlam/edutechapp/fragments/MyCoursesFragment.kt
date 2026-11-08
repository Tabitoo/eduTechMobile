package com.unlam.edutechapp.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.unlam.edutechapp.R
import com.unlam.edutechapp.services.EduTechService
import com.unlam.edutechapp.ui.MyCoursesAdapter

class MyCoursesFragment : Fragment(R.layout.my_courses_fragment) {

    private lateinit var rvMyCourses: RecyclerView
    private lateinit var tvEmpty: TextView

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        rvMyCourses = view.findViewById(R.id.rvMyCourses)
        tvEmpty = view.findViewById(R.id.tvMyCoursesEmpty)

        rvMyCourses.layoutManager =
            LinearLayoutManager(requireContext())
    }

    override fun onResume() {
        super.onResume()

        actualizarCursos()
    }

    private fun actualizarCursos() {

        val courses = EduTechService.getMyCourses()

        rvMyCourses.adapter = MyCoursesAdapter(courses)

        tvEmpty.isVisible = courses.isEmpty()
        rvMyCourses.isVisible = courses.isNotEmpty()
    }
}
