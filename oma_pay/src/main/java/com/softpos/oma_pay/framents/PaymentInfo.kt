package com.softpos.oma_pay.framents


import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.softpos.oma_pay.OmaPay
import com.softpos.oma_pay.OtpScreen
import com.softpos.oma_pay.databinding.FragmentPaymentInfoBinding
import java.util.Calendar

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
                // Remove any previously added spaces
                val cleanString = s?.toString()?.replace("\\s".toRegex(), "")

                // Add a space after every 4 digits
                val formattedString = cleanString?.chunked(4)?.joinToString(" ") ?: ""

                // Set the text of the second TextView with the formatted string
                binding.cardNumber.text = formattedString
                binding.editTextCardNumber.removeTextChangedListener(this)
                binding.editTextCardNumber.setText(formattedString)
                binding.editTextCardNumber.setSelection(formattedString.length)
                binding.editTextCardNumber.addTextChangedListener(this)
            }

            override fun afterTextChanged(s: Editable?) {
                // Not needed
            }
        })
        binding.editTextExpiry.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val cleanString = s?.toString()?.replace("\\D".toRegex(), "") ?: ""

                if (cleanString.length >= 4) {
                    val mm = cleanString.substring(0, 2).toIntOrNull() ?: 0
                    val yy = cleanString.substring(2, 4).toIntOrNull() ?: 0

                    // Validate month and year
                    if (mm in 1..12 && yy >= 0 && yy <= 99 && (yy > getCurrentYear() % 100 || (yy == getCurrentYear() % 100 && mm > getCurrentMonth()))) {
                        // Format MMYY to MM/YY
                        val formattedDate = "${cleanString.substring(0, 2)}/${cleanString.substring(2, 4)}"

                        // Set the text of the TextView with the formatted string
                        binding.expiryDate.text = formattedDate

                        // Set the text of the EditText with the formatted string
                        binding.editTextExpiry.removeTextChangedListener(this)
                        binding.editTextExpiry.setText(formattedDate)
                        binding.editTextExpiry.setSelection(formattedDate.length)
                        binding.editTextExpiry.addTextChangedListener(this)
                    } else {
                        // Invalid input, handle accordingly (e.g., clear the EditText or show an error)
                        binding.editTextExpiry.error = "Invalid expiry date"
                    }
                }
            }
            // Helper function to get the current year
            private fun getCurrentYear(): Int {
                return Calendar.getInstance().get(Calendar.YEAR)
            }

            // Helper function to get the current month (1-based index)
            private fun getCurrentMonth(): Int {
                return Calendar.getInstance().get(Calendar.MONTH)
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
//
//            val intent = Intent(activity, OtpScreen::class.java)
//            activity?.startActivity(intent)


           OmaPay.getInstance().changeState(true)

            activity?.finish()



           // val paymentId = performPayment(apiKey, amount, description)
           // Toast.makeText(context, "Response: $paymentId", Toast.LENGTH_SHORT).show()
           // (activity as? PaymentActivity)?.finish()
        }




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
        val customColor = Color.parseColor("#4290f4")
        binding.cardNumberLayout.boxStrokeColor = customColor
        return binding.root
    }

    private fun performPayment(apiKey: String?, amount: String?, description: String?): Any {
return "Dummy iD"
        OmaPay.getInstance().changeState(true)
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