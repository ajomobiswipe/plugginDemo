package com.example.pluggindemo

import android.app.Activity
import android.content.Intent

class CustomModel private constructor() {

    interface OnCustomStateListener {
        fun onPaymentSuccess(message:String)
        fun stateDeclined()
    }

    companion object {
        private var mInstance: CustomModel? = null

        @JvmStatic
        fun getInstance(): CustomModel {
            if (mInstance == null) {
                mInstance = CustomModel()
            }
            return mInstance as CustomModel
        }
    }
    fun start(activity: Activity,listener: OnCustomStateListener) {
        mListener = listener
        val intent = Intent(activity, PaymentActivity::class.java)
        activity.startActivity(intent)
    }
    private var mListener: OnCustomStateListener? = null
    private var mState = false

//    fun setListener(listener: OnCustomStateListener) {
//        mListener = listener
//    }

    fun changeState(state: Boolean) {
        mListener?.let {
            mState = state
            notifyStateChange()
        }
    }

    fun getState(): Boolean {
        return mState
    }

    private fun notifyStateChange() {
        mListener?.onPaymentSuccess("Payment Success")
    }
}
