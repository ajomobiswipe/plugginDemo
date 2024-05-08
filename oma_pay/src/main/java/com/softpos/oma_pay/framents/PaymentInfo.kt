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
import com.google.android.material.snackbar.Snackbar
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
                        val formattedDate =
                            "${cleanString.substring(0, 2)}/${cleanString.substring(2, 4)}"

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

        binding.payButton.setOnClickListener {
            if (checkCreditCardFields()) {
                val intent = Intent(activity, OtpScreen::class.java)
                activity?.startActivity(intent)
            } else {
                Snackbar.make(binding.root, "Please enter Card Details", Snackbar.LENGTH_LONG)
                    .show()
            }

        }

        return binding.root
    }

    private fun checkCreditCardFields(): Boolean {
        val binding = _binding ?: return false
        if (binding.editTextCardNumber.text?.isEmpty() == true) {
            binding.editTextCardNumber.error = "Card number is required"
            return false
        }
        // Check if the card number is valid (you may use a separate function or library for this)
        val cardNumber = binding.editTextCardNumber.text?.toString()
        if (!isValidCardNumber(cardNumber)) {
            binding.editTextCardNumber.error = "Invalid card number"
            return false
        }


        if (binding.editTextExpiry.text?.isEmpty() == true) {
            binding.editTextExpiry.error = "Expiry date is required"
            return false
        }

        // Check if the expiry date is valid (you may use a separate function or library for this)
        val expiryDate = binding.editTextExpiry.text?.toString()
        if (!isValidExpiryDate(expiryDate)) {
            binding.editTextExpiry.error = "Invalid expiry date"
            return false
        }

        if (binding.editTextCvv.text?.isEmpty() == true) {
            binding.editTextCvv.error = "CVV is required"
            return false
        }

        // Check if the CVV is valid (you may use a separate function or library for this)
        val cvv = binding.editTextCvv.text?.toString()
        if (!isValidCvv(cvv)) {
            binding.editTextCvv.error = "Invalid CVV"
            return false
        }
        if (binding.editTextCardHolderName.text?.isEmpty() == true) {
            binding.editTextCardHolderName.error = "Card holder name is required"
            return false
        }
        return true
    }

    // Placeholder function for validating the credit card number
    private fun isValidCardNumber(cardNumber: String?): Boolean {
        // Implement your validation logic here
        // For example, you can use regex to validate the format
        // You may also perform checksum validation according to credit card standards
        return cardNumber?.matches(Regex("^\\d{4} \\d{4} \\d{4} \\d{4}$")) ?: false
    }

    // Placeholder function for validating the expiry date
    private fun isValidExpiryDate(expiryDate: String?): Boolean {
        // Implement your validation logic here
        // For example, you can check if the expiry date is in the future and in a valid format
        // You may also split the expiry date into month and year and perform separate validations
        return expiryDate?.matches(Regex("^\\d{2}/\\d{2}$")) ?: false
    }

    // Placeholder function for validating the CVV
    private fun isValidCvv(cvv: String?): Boolean {
        // Implement your validation logic here
        // For example, you can check if the CVV is a 3 or 4-digit number
        return cvv?.matches(Regex("^\\d{3,4}$")) ?: false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(PaymentInfoViewModel::class.java)
//        // TODO: Use the ViewModel
//    }

}