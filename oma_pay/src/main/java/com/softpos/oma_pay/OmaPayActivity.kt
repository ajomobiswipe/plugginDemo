package com.softpos.oma_pay

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.challengeone.FragmentAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.softpos.oma_pay.databinding.ActivityPaymentBinding

class OmaPayActivity : AppCompatActivity() {
    lateinit var binding: ActivityPaymentBinding
    var tabTitle = arrayOf("Billing Information", "payment Information")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val addressAvailable = intent.getBooleanExtra("ADDRESS_AVAILABLE",false)
        binding = ActivityPaymentBinding.inflate(layoutInflater)

        var pager = binding.viewPager
        pager.adapter = FragmentAdapter(supportFragmentManager, lifecycle,addressAvailable)
        var t1 = binding.tabs

        TabLayoutMediator(t1, pager) { tab, position ->
            tab.text = tabTitle[position]
            // tab.setIcon(tabIcon[position])
        }.attach()


        //            Log.d(PaymentActivity.TAG, "SecondActivity onCreate: State changed to true")
//            val apiKey = intent.getStringExtra("API_KEY")
//            val amount = intent.getStringExtra("AMOUNT")
//            val description = intent.getStringExtra("DESCRIPTION")


        if (addressAvailable) {
            Toast.makeText(this, "visible true", Toast.LENGTH_SHORT).show()
            // Set the visibility of myTextView to VISIBLE
            binding.tabs.visibility = View.VISIBLE
        } else {
            // Set the visibility of myTextView to GONE or INVISIBLE
            binding.tabs.visibility = View.GONE // or View.INVISIBLE
        }

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
