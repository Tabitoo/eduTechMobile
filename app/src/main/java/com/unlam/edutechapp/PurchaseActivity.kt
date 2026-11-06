package com.unlam.edutechapp

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.unlam.edutechapp.data.Course
import com.unlam.edutechapp.enums.PaymentsEnum
import com.unlam.edutechapp.services.EduTechService
import com.unlam.edutechapp.ui.CourseAdapter

class PurchaseActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.purchase_fragment)


    }
}