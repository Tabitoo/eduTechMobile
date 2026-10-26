package com.unlam.edutechapp.payments

class MercadoPago: PaymentMethod() {

    override fun createPayment(amount: Double): Double {
        val commission = amount * 0.02
        return amount + commission
    }

}