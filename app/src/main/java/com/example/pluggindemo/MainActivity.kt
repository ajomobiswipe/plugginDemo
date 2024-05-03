package com.example.pluggindemo

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pluggindemo.databinding.ActivityMainBinding
import com.softpos.oma_pay.OmaPay
import com.softpos.oma_pay.OnCustomStateListener
import com.softpos.oma_pay.models.BillingAddress
import com.softpos.oma_pay.models.PaymentInfo
import com.softpos.oma_pay.models.PaymentInfoReqModel
import com.softpos.oma_pay.models.RequestBodyModel
import com.softpos.oma_pay.models.ShippingAddress

import org.json.JSONObject

class MainActivity : AppCompatActivity(), OnCustomStateListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        var omaPay=OmaPay()
        setContentView(binding.root)
       // CustomModel.getInstance().setListener(this,this)

        val paymentOptions = PaymentInfo(
            apiKey = "your_api_key",
            amount = "100",
            description = "Payment for something", isContainAddress = true
        )
        binding.buyButton.setOnClickListener {

           // val modelState = OmaPay.getInstance().getState()
           // Log.d(TAG, "Current state: $modelState")
           // OmaPay.getInstance().start(this,this,paymentOptions)
            Toast.makeText(this,paymentInfoRequestModel.billingAddress.billingName,Toast.LENGTH_SHORT).show()
            omaPay.makePayment(
                this,
                this,
                paymentOptions,paymentInfoRequestModel
            )
           //Toast.makeText(this, "Buy now clicked", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onPaymentSuccess(message: String) {
        val modelState = OmaPay.getInstance().getState()
        Toast.makeText(this, "Response: $message", Toast.LENGTH_SHORT).show()
        binding.textView.setText("Success $modelState")
        Log.d(TAG,"MainActivity says: Model state changed: $modelState" )

    }

    override fun stateDeclined() {
        binding.textView.setText("declined")
        Toast.makeText(this, "Response: Declined", Toast.LENGTH_SHORT).show()


    }

    companion object {
        private const val TAG = "MainActivity"
    }
    // Dummy data for shipping address
    val shippingAddress = ShippingAddress(
       deliveryName = "John Doe",
       deliveryAddress = "123 Main St",
       deliveryCity =  "Anytown",
      deliveryState =  "AnyState",
      deliveryTel =  "12345",
     deliveryCountry =  "USA",
      deliveryZip =  "123-456-7890"
    )

    // Dummy data for billing address
    val billingAddress = BillingAddress(
       billingName = "John Doe",
       billingZip =  "456 Elm St",
       billingAddress  = "Anytown",
    billingState= "AnyState",
     billingTel =  "54321",
    billingCountry =  "USA",
      billingEmail =  "098-765-4321",
        billingCity = "123-456-7890"
    )

    // Dummy data for payment information request model
    val paymentInfoRequestModel = PaymentInfoReqModel(
       merchantPublicKey =  "YourClassName",
       merchantID =  "1234 5678 9012 3456",
        merchantName= "John Doe",
     amount =  "12",
       cartID = "25",
      orderID = "123",
       currencyCode =  "jj",
        customerID = "dee",
      billingAddress =   billingAddress,
     shippingAddress =    shippingAddress,
       )
//    fun getRequestBody(): RequestBodyModel {
//        val billingAddress = JSONObject().apply {
//            put("billingName", "AJO")
//            put("billingZip", "")
//            put("billingState", "")
//            put("billingCountry", "")
//            put("billingTel", "")
//            put("billingEmail", "")
//            put("billingCity", "")
//            put("billingAddress", "abc street")
//        }
//
//        val shippingAddress = JSONObject().apply {
//            put("deliveryName", "")
//            put("deliveryState", "")
//            put("deliveryCountry", "")
//            put("deliveryTel", "")
//            put("deliveryZip", "")
//            put("deliveryCity", "")
//            put("deliveryAddress", "")
//        }
//
//        val objectBody = JSONObject().apply {
//            put("merchantId", "1")
//            put("merchantName", "EzKart ECOM Provider")
//            put("currencyCode", "AED")
//            put("amount", "1000")
//            put(
//                "merchantPublicKey",
//                "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC5whV5aZ1mPiohGkE7GT2dYpr0iAW6STLbrE2yuT4DPAFRpXgeLJEPCGr2jakHlzEujzX+mr9ZHdY9ukp0ebyWBibWGPpqyVuSqGOx4BAukviltqPCmA+DGffxRcDh7cl+5HPwFCryiD7zKo1BOHCLLZFpck1ClgWETXIqbu8YEQIDAQAB"
//            )
//            put("orderId", "Y665JG")
//            put("cartId", "2")
//            put("billingAddress", billingAddress.toString())
//            put("shippingAddress", shippingAddress.toString())
//            put("customerId", "0000000000001")
//        }
//
//        return RequestBodyModel.fromJson(objectBody)
//    }


}
