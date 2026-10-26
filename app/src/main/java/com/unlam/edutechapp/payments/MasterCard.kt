package com.unlam.edutechapp.payments

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.DayOfWeek
import java.time.LocalDate

class MasterCard: PaymentMethod() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun createPayment(amount: Double): Double {

        val today: LocalDate = LocalDate.now()

        var generalCommission: Double = amount * 0.0075

        if(today.dayOfWeek === DayOfWeek.SATURDAY || today.dayOfWeek === DayOfWeek.SUNDAY) {
            generalCommission = amount * 0.03

        }
        return amount + generalCommission
    }

}