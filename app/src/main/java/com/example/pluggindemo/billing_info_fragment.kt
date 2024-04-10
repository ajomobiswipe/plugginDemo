package com.example.pluggindemo

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pluggindemo.databinding.FragmentBillingInfoFragmentBinding
import java.io.Console

class BillingInfoFragment : Fragment() {
    private var _binding: FragmentBillingInfoFragmentBinding?=null
    private val binding get()=_binding!!
    companion object {
        fun newInstance() = BillingInfoFragment()
    }

    private lateinit var viewModel: BillingInfoFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BillingInfoFragmentViewModel::class.java)

       //  viewModel.tabTitle[0]
    }

}