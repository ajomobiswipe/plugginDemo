package com.example.pluggindemo.framents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.pluggindemo.CustomModel
import com.example.pluggindemo.PaymentActivity
import com.example.pluggindemo.databinding.FragmentBillingInfoFragmentBinding

class BillingInfoFragment : Fragment() {
    private var _binding: FragmentBillingInfoFragmentBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentBillingInfoFragmentBinding.inflate(inflater, container, false)
        binding.declineButton.setOnClickListener {
            CustomModel.getInstance().changeState(true)
           (activity as? PaymentActivity)?.binding?.viewPager?.setCurrentItem(1, true)

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

