package com.example.pluggindemo

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.pluggindemo.databinding.ActivityMainBinding
import com.example.pluggindemo.databinding.ActivityPaymentBinding

class PaymentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPaymentBinding
    var tabTitle= arrayOf("Billing Information","payment Information")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)

        if (intent.getBooleanExtra("ADDRESS_AVAILABLE",false)){
            setContentView(binding.root)
        }
       // setContentView(binding.root)


        binding.payButton.setOnClickListener {
            CustomModel.getInstance().changeState(true)
            Log.d(TAG, "SecondActivity onCreate: State changed to true")
            val apiKey = intent.getStringExtra("API_KEY")
            val amount = intent.getStringExtra("AMOUNT")
            val description = intent.getStringExtra("DESCRIPTION")
            val addressAvailable = intent.getBooleanExtra("ADDRESS_AVAILABLE",false)

            // Perform the payment logic here using apiKey, amount, and description
            val paymentId = performPayment(apiKey, amount, description)
            Toast.makeText(this, "Response: $paymentId", Toast.LENGTH_SHORT).show()
            finish()
        }

        binding.declineButton.setOnClickListener {
            CustomModel.getInstance().changeState(false)
            finish()
        }

    }


    private fun performPayment(apiKey: String?, amount: String?, description: String?): String? {
        // Your payment logic goes here
        // Return the payment ID if successful, null otherwise
        return "dummyPaymentId"
    }
    companion object {
        private const val TAG = "SecondActivity"
    }
}
