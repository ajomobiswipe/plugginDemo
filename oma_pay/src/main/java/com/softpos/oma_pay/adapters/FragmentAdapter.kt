package com.example.challengeone

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.softpos.oma_pay.framents.BillingInfoFragment
import com.softpos.oma_pay.framents.PaymentInfoFragment


class FragmentAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val isContainAddress: Boolean
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return if (isContainAddress) 2 else 1
    }

    override fun createFragment(position: Int): Fragment {
        return if (isContainAddress) {

            when (position) {
                0 -> BillingInfoFragment()
                1 -> PaymentInfoFragment()
                else -> BillingInfoFragment()
            }

        } else {
            PaymentInfoFragment()
        }
    }
}
