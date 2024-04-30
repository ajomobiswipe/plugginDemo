package com.example.pluggindemo

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.pluggindemo.databinding.ActivityMainBinding
import com.softpos.oma_pay.OmaPay
import com.softpos.oma_pay.OmaPayActivity
import com.softpos.oma_pay.OnCustomStateListener
import com.softpos.oma_pay.models.PaymentInfo

class MainActivity : AppCompatActivity(), OnCustomStateListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
       // CustomModel.getInstance().setListener(this,this)

        val paymentOptions = PaymentInfo(
            apiKey = "your_api_key",
            amount = "100",
            description = "Payment for something", isContainAddress = true
        )
        binding.buyButton.setOnClickListener {

            val modelState = OmaPay.getInstance().getState()
            Log.d(TAG, "Current state: $modelState")
            OmaPay.getInstance().start(this,this,paymentOptions)
           Toast.makeText(this, "Buy now clicked", Toast.LENGTH_SHORT).show()
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
//
//    companion object {
//        private const val TAG = "MainActivity"
//    }

}
