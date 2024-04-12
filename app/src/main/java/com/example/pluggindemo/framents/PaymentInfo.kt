package com.example.pluggindemo.framents

import android.content.Intent
import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.pluggindemo.CustomModel
import com.example.pluggindemo.PaymentActivity
import com.example.pluggindemo.R
import com.example.pluggindemo.ViewModels.PaymentInfoViewModel
import com.example.pluggindemo.databinding.FragmentBillingInfoFragmentBinding
import com.example.pluggindemo.databinding.FragmentPaymentInfoBinding

class PaymentInfoFragment : Fragment() {

    private var _binding: FragmentPaymentInfoBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPaymentInfoBinding.inflate(inflater, container, false)
        binding.editTextCardHolderName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Set the text of the second TextView when the card holder name changes
                binding.cardHolderName.text = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
                // Not needed
            }
        })

        binding.editTextCardNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Set the text of the second TextView when the card holder name changes
                binding.cardNumber.text = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
                // Not needed
            }
        })
//        binding.declineButton.setOnClickListener {
//            CustomModel.getInstance().changeState(true)
//            // (activity as? PaymentActivity)?.binding?.viewPager?.setCurrentItem(1, true)
//
//            Toast.makeText(context, "next clicked", Toast.LENGTH_SHORT).show()
//            (activity as? PaymentActivity)?.finish()
//        }
        binding.payButton.setOnClickListener {
           val intent = Intent()
            CustomModel.getInstance().changeState(true)
            Log.d(PaymentActivity.TAG, "SecondActivity onCreate: State changed to true")
            val apiKey = intent.getStringExtra("API_KEY")
            val amount = intent.getStringExtra("AMOUNT")
            val description = intent.getStringExtra("DESCRIPTION")
            val addressAvailable = intent.getBooleanExtra("ADDRESS_AVAILABLE",false)


binding.editTextCardNumber.addTextChangedListener(object : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        // No implementation needed
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        if (!s.isNullOrEmpty()) {
            binding.cardNumberLayout.isHintEnabled = true
        } else {
            binding.cardNumberLayout.isHintEnabled = false
        }

       // (!s.isNullOrEmpty()).also { binding.outlinedTextField.isHintEnabled = it }
    }

    override fun afterTextChanged(s: Editable?) {
        // No implementation needed
    }
})
            val paymentId = performPayment(apiKey, amount, description)
            Toast.makeText(context, "Response: $paymentId", Toast.LENGTH_SHORT).show()
            (activity as? PaymentActivity)?.finish()
        }
        val customColor = Color.parseColor("#4290f4")
        binding.cardNumberLayout.boxStrokeColor = customColor
        return binding.root
    }

    private fun performPayment(apiKey: String?, amount: String?, description: String?): Any {
return "Dummy iD"
        CustomModel.getInstance().changeState(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
//        binding.payButton.setOnClickListener {
//           val intent = Intent()
//            CustomModel.getInstance().changeState(true)
//            Log.d(PaymentActivity.TAG, "SecondActivity onCreate: State changed to true")
//            val apiKey = intent.getStringExtra("API_KEY")
//            val amount = intent.getStringExtra("AMOUNT")
//            val description = intent.getStringExtra("DESCRIPTION")
//            val addressAvailable = intent.getBooleanExtra("ADDRESS_AVAILABLE",false)
//
//            // Perform the payment logic here using apiKey, amount, and description
//           // val paymentId = performPayment(apiKey, amount, description)
//           // Toast.makeText(this, "Response: $paymentId", Toast.LENGTH_SHORT).show()
//           // finish()
//        }
//
//        binding.declineButton.setOnClickListener {
//            CustomModel.getInstance().changeState(false)
//           // finish()
//        }
//        return inflater.inflate(R.layout.fragment_payment_info, container, false)
//    }
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(PaymentInfoViewModel::class.java)
//        // TODO: Use the ViewModel
//    }

}