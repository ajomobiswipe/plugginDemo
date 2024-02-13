package com.example.pluggindemo

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.pluggindemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),CustomModel.OnCustomStateListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
       // CustomModel.getInstance().setListener(this,this)
        binding.buyButton.setOnClickListener {
            val modelState = CustomModel.getInstance().getState()
            Log.d(TAG, "Current state: $modelState")
            CustomModel.getInstance().start(this,this)
 //           val intent = Intent(this, PaymentActivity::class.java)
//            startActivity(intent)
           Toast.makeText(this, "Buy now clicked", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onPaymentSuccess(message: String) {
        val modelState = CustomModel.getInstance().getState()
        Toast.makeText(this, "Response: $message", Toast.LENGTH_SHORT).show()
        binding.textView.setText("Succes $modelState")
        Log.d(TAG,"MainActivity says: Model state changed: $modelState" )
    }

    override fun stateDeclined() {
        TODO("Not yet implemented")
    }

    companion object {
        private const val TAG = "MainActivity"
    }

}
