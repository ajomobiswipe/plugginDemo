package com.example.pluggindemo

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.pluggindemo.databinding.ActivityOtpScreenBinding

class OtpScreen : AppCompatActivity() {

    private lateinit var binding: ActivityOtpScreenBinding
    private val pinFields = mutableListOf<EditText>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pinFields.add(binding.pinField1)
        pinFields.add(binding.pinField2)
        pinFields.add(binding.pinField3)
        pinFields.add(binding.pinField4)
        pinFields.add(binding.pinField5)
        pinFields.add(binding.pinField6)
        // Add remaining EditText fields to pinFields list

        setupPinFields()
        // Set focus on the first PIN field
        pinFields.firstOrNull()?.requestFocus()
        binding.btnPayNow.setOnClickListener {

            showAlert(this,"Success","Payment Success")
        }
    }
    fun showAlert(context: Context, title: String, message: String) {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle(title)
        alertDialogBuilder.setMessage(message)

        // Add positive button
        alertDialogBuilder.setPositiveButton("OK") { dialog, which ->
            // Positive button click listener
            // You can add your action here if needed
            dialog.dismiss()
        }

        // Add negative button
        alertDialogBuilder.setNegativeButton("Cancel") { dialog, which ->
            // Negative button click listener
            // You can add your action here if needed
            dialog.dismiss()
        }

        // Create and show the dialog
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }



//    fun showCustomDialog(context: Context) {
//        // Inflate the custom layout
//        val inflater = LayoutInflater.from(context)
//        val view = inflater.inflate(R.layout.custom_dialog_layout, null)
//
//        // Build the AlertDialog
//        val alertDialogBuilder = AlertDialog.Builder(context)
//        alertDialogBuilder.setView(view)
//
//        // Set click listener for positive button
//        view.positiveButton.setOnClickListener {
//            // Handle positive button click
//            // For example, you can dismiss the dialog
//            alertDialog.dismiss()
//        }
//
//        // Set click listener for negative button
//        view.negativeButton.setOnClickListener {
//            // Handle negative button click
//            // For example, you can dismiss the dialog
//            alertDialog.dismiss()
//        }
//
//        // Create and show the dialog
//        val alertDialog = alertDialogBuilder.create()
//        alertDialog.show()
//    }

    private fun setupPinFields() {
        for (i in 0 until pinFields.size) {
            val editText = pinFields[i]
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s?.length == 1 && i < pinFields.size - 1) {
                        pinFields[i + 1].requestFocus()
                    } else if (s?.isEmpty() == true && i > 0) {
                        pinFields[i - 1].requestFocus()
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    // Check if PIN entry is complete
                    if (s?.length == 1 && i == pinFields.size - 1) {
                        onPinEntryComplete()
                    }
                }
            })

            editText.setOnKeyListener { _, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL && editText.text.isEmpty() && i > 0) {
                    pinFields[i - 1].requestFocus()
                    true
                } else {
                    false
                }
            }
        }
    }

    private fun onPinEntryComplete() {
        // Get the entered PIN from the fields
        val enteredPin = getPinFromFields()
        // You can implement OTP verification logic here
        Toast.makeText(this, "Entered PIN: $enteredPin", Toast.LENGTH_SHORT).show()
    }

    private fun getPinFromFields(): String {
        val pin = StringBuilder()
        for (editText in pinFields) {
            pin.append(editText.text)
        }
        return pin.toString()
    }
}
