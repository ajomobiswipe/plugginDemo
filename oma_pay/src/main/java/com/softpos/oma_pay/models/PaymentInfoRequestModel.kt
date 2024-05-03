package com.softpos.oma_pay.models

// To parse the JSON, install kotlin's serialization plugin and do:
//
// val json                    = Json { allowStructuredMapKeys = true }
// val paymentInfoRequestModel = json.parse(PaymentInfoRequestModel.serializer(), jsonString)


data class PaymentInfoReqModel(

    val merchantID: String,
    val merchantName: String,
    val currencyCode: String,
    val amount: String,
    val merchantPublicKey: String,
    val orderID: String,
    val cartID: String,
    val customerID: String,
    val billingAddress: BillingAddress,
    val shippingAddress: ShippingAddress

)


data class BillingAddress(
    val billingName: String,
    val billingZip: String,
    val billingState: String,
    val billingCountry: String,
    val billingTel: String,
    val billingEmail: String,
    val billingCity: String,
    val billingAddress: String
)


data class ShippingAddress(
    val deliveryName: String,
    val deliveryState: String,
    val deliveryCountry: String,
    val deliveryTel: String,
    val deliveryZip: String,
    val deliveryCity: String,
    val deliveryAddress: String
)

