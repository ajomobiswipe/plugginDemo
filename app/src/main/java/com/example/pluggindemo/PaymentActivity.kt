package com.example.pluggindemo

import ViewPagerAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pluggindemo.databinding.ActivityPaymentBinding
import com.example.pluggindemo.framents.BillingInfoFragment
import com.example.pluggindemo.framents.PaymentInfoFragment

class PaymentActivity : AppCompatActivity() {
    lateinit var binding: ActivityPaymentBinding
    var tabTitle= arrayOf("Billing Information","payment Information")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityPaymentBinding.inflate(layoutInflater)


        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(BillingInfoFragment(), tabTitle[0])
        adapter.addFragment(PaymentInfoFragment(), tabTitle[1])
        binding.viewPager.adapter = adapter
       binding.tabs.setupWithViewPager(binding.viewPager)

        setContentView(binding.root)



    }




    companion object {
        const val TAG = "SecondActivity"
    }

}
