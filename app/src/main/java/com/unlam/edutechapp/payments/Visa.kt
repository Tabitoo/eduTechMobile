package com.unlam.edutechapp.payments
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalTime

class Visa: PaymentMethod() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun createPayment(amount: Double): Double {

        val now: LocalTime = LocalTime.now()
        var generalCommission: Double = amount * 0.03

        if(now.isAfter(LocalTime.of(14, 59)) && now.isBefore(LocalTime.of(22, 29))) {
            generalCommission = amount * 0.01
        }

        return amount + generalCommission
    }

}