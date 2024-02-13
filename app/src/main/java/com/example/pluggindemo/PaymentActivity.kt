package com.example.pluggindemo

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.pluggindemo.databinding.ActivityMainBinding
import com.example.pluggindemo.databinding.ActivityPaymentBinding

class PaymentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPaymentBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.payButton.setOnClickListener {
            CustomModel.getInstance().changeState(true)
            Log.d(TAG, "SecondActivity onCreate: State changed to true")

            finish()
        }

        binding.declineButton.setOnClickListener {
            CustomModel.getInstance().changeState(false)
        }

    }
    companion object {
        private const val TAG = "SecondActivity"
    }
}
