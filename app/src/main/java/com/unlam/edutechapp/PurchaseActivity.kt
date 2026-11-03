package com.unlam.edutechapp

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.unlam.edutechapp.data.Course
import com.unlam.edutechapp.repositories.CourseRepository
import com.unlam.edutechapp.services.EduTechService
import com.unlam.edutechapp.ui.CourseAdapter

class PurchaseActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_purchase)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val courses: MutableList<Course> = intent.getSerializableExtra("COURSES", ArrayList::class.java) as MutableList<Course>
        val rvCourses = findViewById<RecyclerView>(R.id.rvCourses)
        val tvTotalPrice = findViewById<TextView>(R.id.tvTotalAmount)
        val rgPaymentMethod = findViewById<RadioGroup>(R.id.rgPaymentMethod)
        val btnPurchase = findViewById<Button>(R.id.btnPurchase)
        rvCourses.layoutManager = LinearLayoutManager(this)
        rvCourses.adapter = CourseAdapter(courses)
        tvTotalPrice.text = "Precio total: ${courses.size * 10000 }"
        rgPaymentMethod.setOnCheckedChangeListener { _, checkedId ->
            btnPurchase.isEnabled = checkedId != -1
            val method = when (checkedId) {
                R.id.rbMercadoPago -> "Mercado Pago"
                R.id.rbVisa -> "Visa"
                R.id.rbMastercard -> "Mastercard"
                else -> null
            }
            btnPurchase.text = "Pagar con ${ method ?: "metodo de pago"}"

        }

        btnPurchase.setOnClickListener {

            when(rgPaymentMethod.checkedRadioButtonId) {
                R.id.rbMercadoPago -> {


                }

            }

            Toast.makeText(this, "Elegi un metodo de pago", Toast.LENGTH_SHORT).show()
        }

    }


}