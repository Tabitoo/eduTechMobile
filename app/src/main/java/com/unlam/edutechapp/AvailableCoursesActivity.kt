package com.unlam.edutechapp
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.unlam.edutechapp.data.Course
import com.unlam.edutechapp.services.EduTechService
import com.unlam.edutechapp.ui.AvailableCoursesAdapter
import java.io.Serializable

class AvailableCoursesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.available_courses_fragment)
        val courses = EduTechService.getAvailableCourses()
        val selectedCourses: MutableList<Course> = mutableListOf()
        val tvTotalCourse = findViewById<TextView>(R.id.tvTotalCourses)
        val rvAvailableCourse = findViewById<RecyclerView>(R.id.rvAvailableCourses)
        val tvCourseEmpty = findViewById<TextView>(R.id.tvEmpty)
        val btnCourseCart = findViewById<Button>(R.id.btnCoursePurchase)
        rvAvailableCourse.layoutManager = LinearLayoutManager(this)
        rvAvailableCourse.adapter = AvailableCoursesAdapter(courses) {course ->

            if(selectedCourses.contains(course)) {
                Log.d("CONDIDIONAL:", "course ya agregado")
            } else {
                selectedCourses.add((course))
                tvTotalCourse.text = "Cursos Seleccionados ${selectedCourses.size}"
                Log.d("VALOR DE COURSE LIST: ", "$selectedCourses")

            }
        }

        btnCourseCart.setOnClickListener {
            val intent = Intent(this, PurchaseActivity::class.java)
            intent.putExtra("COURSES", selectedCourses as Serializable)
            startActivity(intent)
        }

        tvCourseEmpty.isVisible = courses.isEmpty()
        rvAvailableCourse.isVisible = courses.isNotEmpty()
    }
}

