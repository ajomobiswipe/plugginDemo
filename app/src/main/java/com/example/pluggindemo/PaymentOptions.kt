package com.example.pluggindemo

data class PaymentOptions(
    val apiKey: String,
    val amount: String,
    val isContainAddress: Boolean,
    val description: String
    )
