package com.softpos.oma_pay.framents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.softpos.oma_pay.OmaPay
import com.softpos.oma_pay.OmaPayActivity
import com.softpos.oma_pay.databinding.FragmentBillingInfoFragmentBinding

class BillingInfoFragment : Fragment() {
    private var _binding: FragmentBillingInfoFragmentBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentBillingInfoFragmentBinding.inflate(inflater, container, false)
        var billingAddress= OmaPay.getInstance().billingreq?.billingAddress
        var shippingAddress= OmaPay.getInstance().billingreq?.shippingAddress
        if (billingAddress != null) {
            _binding!!.editTextBillingAddress.setText(billingAddress.billingAddress)
            _binding!!.editTextBillingName.setText(billingAddress.billingName)
            _binding!!.editTextBillingState.setText(billingAddress.billingState)
            _binding!!.editTextBillingCity.setText(billingAddress.billingCity)
            _binding!!.editTextContactNumber.setText(billingAddress.billingTel)
            _binding!!.editTextZipCode.setText(billingAddress.billingTel)
            _binding!!.editTextCountry.setText(billingAddress.billingCountry)
            _binding!!.editTextEmail.setText(billingAddress.billingEmail)



        }
        if (shippingAddress != null) {
            _binding!!.editTextShippingName.setText(shippingAddress.deliveryName)
            _binding!!.editTextShippingAddress.setText(shippingAddress.deliveryAddress)
            _binding!!.editTextShippingCity.setText(shippingAddress.deliveryCity)
            _binding!!.editTextShippingState.setText(shippingAddress.deliveryState)
            _binding!!.editTextShippingCountry.setText(shippingAddress.deliveryCountry)
            _binding!!.editTextShippingZipCode.setText(shippingAddress.deliveryZip)
            _binding!!.editTextShippingContactNumber.setText(shippingAddress.deliveryTel)

        }
binding.addressSame.isChecked

        binding.addressSame.setOnClickListener{
            if(binding.addressSame.isChecked){

                binding.shoppingLayout.visibility=View.GONE
            }else{
                binding.shoppingLayout.visibility=View.VISIBLE
            }
        }
        binding.buttonNext.setOnClickListener {
            if (checkBillingAddressFields()) {
                if (!binding.addressSame.isChecked) {
                    // Move to the next step if shipping address is different
                    if (checkShippingAddressFields()) {
                        (activity as? OmaPayActivity)?.binding?.viewPager?.setCurrentItem(1, true)
                    } else {
                        val snackbar = Snackbar.make(binding.root, "Please enter Shipping Details", Snackbar.LENGTH_LONG)
                            .setAction("Ok") {
                                binding.billingScrollView.fullScroll(View.FOCUS_UP)
                            }
                        snackbar.show()
                    }
                } else {
                    // Move to the next step if shipping address is the same as billing address
                    (activity as? OmaPayActivity)?.binding?.viewPager?.setCurrentItem(1, true)
                }
            } else {
                val snackbar = Snackbar.make(binding.root, "Please enter Billing Details", Snackbar.LENGTH_LONG)
                    .setAction("Ok") {
                        binding.billingScrollView.fullScroll(View.FOCUS_UP)
                    }
                snackbar.show()
            }
        }


        return binding.root
    }
    private fun checkBillingAddressFields(): Boolean {
        val billingAddress = _binding ?: return false

        if (billingAddress.editTextBillingAddress.text?.isEmpty() == true) {
            billingAddress.editTextBillingAddress.error = "Billing address is required"
            return false
        }

        if (billingAddress.editTextBillingName.text?.isEmpty() == true) {
            billingAddress.editTextBillingName.error = "Billing name is required"
            return false
        }

        if (billingAddress.editTextBillingState.text?.isEmpty() == true) {
            billingAddress.editTextBillingState.error = "Billing state is required"
            return false
        }

        if (billingAddress.editTextBillingCity.text?.isEmpty() == true) {
            billingAddress.editTextBillingCity.error = "Billing city is required"
            return false
        }

        if (billingAddress.editTextContactNumber.text?.isEmpty() == true) {
            billingAddress.editTextContactNumber.error = "Contact number is required"
            return false
        }

        if (billingAddress.editTextZipCode.text?.isEmpty() == true) {
            billingAddress.editTextZipCode.error = "Zip code is required"
            return false
        }

        if (billingAddress.editTextCountry.text?.isEmpty() == true) {
            billingAddress.editTextCountry.error = "Country is required"
            return false
        }

        if (billingAddress.editTextEmail.text?.isEmpty() == true) {
            billingAddress.editTextEmail.error = "Email is required"
            return false
        }

        // Add any additional validations here

        return true
    }
    private fun checkShippingAddressFields(): Boolean {
        val shippingAddress = _binding ?: return false

        if ((shippingAddress.editTextShippingName.text?.length ?: 0) < 3) {
            shippingAddress.editTextShippingName.error = "Shipping name must be at least 3 characters"
            return false
        }

        if (shippingAddress.editTextShippingAddress.text?.length ?: 0 < 3) {
            shippingAddress.editTextShippingAddress.error = "Shipping address must be at least 3 characters"
            return false
        }

        if (shippingAddress.editTextShippingCity.text?.length ?: 0 < 3) {
            shippingAddress.editTextShippingCity.error = "Shipping city must be at least 3 characters"
            return false
        }

        if (shippingAddress.editTextShippingState.text?.length ?: 0 < 3) {
            shippingAddress.editTextShippingState.error = "Shipping state must be at least 3 characters"
            return false
        }

        if (shippingAddress.editTextShippingCountry.text?.length ?: 0 < 3) {
            shippingAddress.editTextShippingCountry.error = "Shipping country must be at least 3 characters"
            return false
        }

        if (shippingAddress.editTextShippingZipCode.text?.length ?: 0 < 3) {
            shippingAddress.editTextShippingZipCode.error = "Shipping zip code must be at least 3 characters"
            return false
        }

        if (shippingAddress.editTextShippingContactNumber.text?.length ?: 0 < 3) {
            shippingAddress.editTextShippingContactNumber.error = "Shipping contact number must be at least 3 characters"
            return false
        }

        // Add any additional validations here

        return true
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

