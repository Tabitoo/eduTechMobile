package com.unlam.edutechapp.payments

abstract class PaymentMethod {

    abstract fun createPayment(amount: Double): Double

}