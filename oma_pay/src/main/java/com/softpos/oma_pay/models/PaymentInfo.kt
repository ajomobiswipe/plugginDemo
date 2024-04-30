package com.softpos.oma_pay.models

data class PaymentInfo(
    val apiKey: String,
    val amount: String,
    val isContainAddress: Boolean,
    val description: String
)