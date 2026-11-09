package com.unlam.edutechapp.fragments

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.unlam.edutechapp.R
import com.unlam.edutechapp.enums.PaymentsEnum
import com.unlam.edutechapp.exceptions.CourseAlreadyPurchasedException
import com.unlam.edutechapp.repositories.CourseRepository
import com.unlam.edutechapp.services.EduTechService
import com.unlam.edutechapp.ui.CoursePurchaseAdapter

class PurchaseFragment: Fragment(R.layout.purchase_fragment) {

    val args: PurchaseFragmentArgs by navArgs()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val coursesId: LongArray = args.courseIds
        val courses = CourseRepository.getByCourseIds(coursesId.toSet())
        val rvCourses = view.findViewById<RecyclerView>(R.id.rvCourses)
        val tvTotalPrice = view.findViewById<TextView>(R.id.tvTotalAmount)
        val tvPurchasedCourse = view.findViewById<TextView>(R.id.tvCoursePurchased)
        val rgPaymentMethod = view.findViewById<RadioGroup>(R.id.rgPaymentMethod)
        val btnPurchase = view.findViewById<Button>(R.id.btnPurchase)
        rvCourses.layoutManager = LinearLayoutManager(requireContext())
        rvCourses.adapter = CoursePurchaseAdapter(courses)
        tvTotalPrice.text = "Precio total: ${courses.size * 10000}"
        rgPaymentMethod.setOnCheckedChangeListener { _, checkedId ->
            btnPurchase.isEnabled = checkedId != -1
            val method = when (checkedId) {
                R.id.rbMercadoPago -> PaymentsEnum.MERCADOPAGO
                R.id.rbVisa -> PaymentsEnum.VISA
                R.id.rbMastercard -> PaymentsEnum.MASTERCARD
                else -> null
            }
            btnPurchase.text = "Pagar con ${method ?: "metodo de pago"}"

        }

        btnPurchase.setOnClickListener {
            try {

                val coursesId: Set<Long> = courses.map { course ->
                    course.id
                }.toSet()

                val resultPayment =
                    EduTechService.purchaseCourse(coursesId, PaymentsEnum.MERCADOPAGO)

                Toast.makeText(
                    requireContext(),
                    "Resultado: Pago realizado de forma correcta!",
                    Toast.LENGTH_SHORT
                ).show()

                findNavController().navigate(R.id.action_purchaseFragment_to_myCoursesFragment)


            } catch (e: CourseAlreadyPurchasedException) {
                tvPurchasedCourse.isVisible = true
                val defaultText = getString(R.string.curso_comprado)
                tvPurchasedCourse.text = "$defaultText ${e.courses.joinToString { it.name }}"
            }


        }
    }

}