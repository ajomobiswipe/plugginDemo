package com.softpos.oma_pay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.challengeone.FragmentAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.softpos.oma_pay.databinding.ActivityPaymentBinding

class OmaPayActivity : AppCompatActivity() {
    lateinit var binding: ActivityPaymentBinding
    var tabTitle = arrayOf("Billing Information", "payment Information")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)

        var pager = binding.viewPager
        pager.adapter = FragmentAdapter(supportFragmentManager, lifecycle)
        var t1 = binding.tabs

        TabLayoutMediator(t1, pager) { tab, position ->
            tab.text = tabTitle[position]
            // tab.setIcon(tabIcon[position])
        }.attach()
//        val adapter = ViewPagerAdapter(supportFragmentManager)
//        adapter.addFragment(BillingInfoFragment(), tabTitle[0])
//        adapter.addFragment(PaymentInfoFragment(), tabTitle[1])
//        binding.viewPager.adapter = adapter
//       binding.tabs.setupWithViewPager(binding.viewPager)

        setContentView(binding.root)
        OmaPayActivity()

    }


    companion object {
        const val TAG = "SecondActivity"
    }
}
