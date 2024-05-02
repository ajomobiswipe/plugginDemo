package com.softpos.oma_pay.models

import org.json.JSONObject

data class RequestBodyModel(
    val merchantId: String?,
    val merchantName: String?,
    val currencyCode: String?,
    val amount: String?,
    val merchantPublicKey: String?,
    val orderId: String?,
    val cartId: String?,
    val customerId: String?,
    val billingAddress: BillingAddressModel?,
    val shippingAddress: ShippingAddressModel?
) {
    companion object {
        fun fromJson(json: JSONObject): RequestBodyModel {
            return RequestBodyModel(
                json.optString("merchantId", null),
                json.optString("merchantName", null),
                json.optString("currencyCode", null),
                json.optString("amount", null),
                json.optString("merchantPublicKey", null),
                json.optString("orderId", null),
                json.optString("cartId", null),
                json.optString("customerId", null),
                billingAddressModelFromJson(json.optJSONObject("billingAddress")),
                shippingAddressModelFromJson(json.optJSONObject("shippingAddress"))
            )
        }
    }
}

data class ShippingAddressModel(
    val deliveryName: String?,
    val deliveryState: String?,
    val deliveryCountry: String?,
    val deliveryTel: String?,
    val deliveryZip: String?,
    val deliveryCity: String?,
    val deliveryAddress: String?
) {
    companion object {
        fun fromJson(json: JSONObject): ShippingAddressModel {
            return ShippingAddressModel(
                json.optString("deliveryName", null),
                json.optString("deliveryState", null),
                json.optString("deliveryCountry", null),
                json.optString("deliveryTel", null),
                json.optString("deliveryZip", null),
                json.optString("deliveryCity", null),
                json.optString("deliveryAddress", null)
            )
        }
    }
}

data class BillingAddressModel(
    val billingName: String?,
    val billingZip: String?,
    val billingState: String?,
    val billingCountry: String?,
    val billingTel: String?,
    val billingEmail: String?,
    val billingCity: String?,
    val billingAddress: String?
) {
    companion object {
        fun fromJson(json: JSONObject): BillingAddressModel {
            return BillingAddressModel(
                json.optString("billingName", null),
                json.optString("billingZip", null),
                json.optString("billingState", null),
                json.optString("billingCountry", null),
                json.optString("billingTel", null),
                json.optString("billingEmail", null),
                json.optString("billingCity", null),
                json.optString("billingAddress", null)
            )
        }
    }
}

fun shippingAddressModelFromJson(json: JSONObject?): ShippingAddressModel? {
    return json?.let { ShippingAddressModel.fromJson(it) }
}

fun billingAddressModelFromJson(json: JSONObject?): BillingAddressModel? {
    return json?.let { BillingAddressModel.fromJson(it) }
}
