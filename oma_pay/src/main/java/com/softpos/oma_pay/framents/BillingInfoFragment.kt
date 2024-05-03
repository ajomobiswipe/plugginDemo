package com.softpos.oma_pay.framents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
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
            OmaPay.getInstance().changeState(true)
           (activity as? OmaPayActivity)?.binding?.viewPager?.setCurrentItem(1, true)

//            Toast.makeText(context, "next clicked", Toast.LENGTH_SHORT).show()
//            (activity as? PaymentActivity)?.finish()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

