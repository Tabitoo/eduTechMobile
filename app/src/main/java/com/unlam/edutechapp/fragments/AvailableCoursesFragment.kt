package com.unlam.edutechapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.unlam.edutechapp.R
import com.unlam.edutechapp.data.Course
import com.unlam.edutechapp.services.EduTechService
import com.unlam.edutechapp.ui.AvailableCoursesAdapter

class AvailableCoursesFragment: Fragment(R.layout.available_courses_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val courses = EduTechService.getAvailableCourses()
        val selectedCourses: MutableList<Course> = mutableListOf()
        val tvTotalCourse = view.findViewById<TextView>(R.id.tvTotalCourses)
        val rvAvailableCourse = view.findViewById<RecyclerView>(R.id.rvAvailableCourses)
        val tvCourseEmpty = view.findViewById<TextView>(R.id.tvEmpty)
        val btnCourseCart = view.findViewById<Button>(R.id.btnCoursePurchase)
        rvAvailableCourse.layoutManager = LinearLayoutManager(requireContext())
        rvAvailableCourse.adapter = AvailableCoursesAdapter(courses) {course ->

            if(!selectedCourses.contains(course)) {

                selectedCourses.add((course))
                tvTotalCourse.text = "Cursos Seleccionados ${selectedCourses.size}"
            }
        }

        btnCourseCart.setOnClickListener {
            val action = AvailableCoursesFragmentDirections
                .actionAvailableCoursesFragmentToPurchaseFragment(selectedCourses.map {
                    it.id
                }.toLongArray())
            findNavController().navigate(action)

        }

        tvCourseEmpty.isVisible = courses.isEmpty()
        rvAvailableCourse.isVisible = courses.isNotEmpty()

    }
}