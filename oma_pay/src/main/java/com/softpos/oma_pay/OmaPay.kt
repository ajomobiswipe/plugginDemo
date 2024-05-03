package com.softpos.oma_pay

import android.app.Activity
import android.content.Intent
import com.softpos.oma_pay.models.PaymentInfo
import com.softpos.oma_pay.models.RequestBodyModel

class OmaPay  constructor() {

    companion object {
        private var mInstance: OmaPay? = null

        @JvmStatic
        fun getInstance(): OmaPay {
            if (mInstance == null) {
                mInstance = OmaPay()
            }
            return mInstance as OmaPay
        }
    }
    private fun start(activity: Activity, listener: OnCustomStateListener, options: PaymentInfo,reqModel: RequestBodyModel) {

        mListener = listener
        val intent = Intent(activity, OmaPayActivity::class.java)
        // Pass payment options to PaymentActivity
        intent.putExtra("API_KEY", options.apiKey)
        intent.putExtra("AMOUNT", options.amount)
        intent.putExtra("DESCRIPTION", options.description)
        intent.putExtra("ADDRESS_AVAILABLE", options.isContainAddress)

        activity.startActivity(intent)
    }

    fun makePayment(activity: Activity,listener: OnCustomStateListener, options: PaymentInfo,reqModel: RequestBodyModel) {

        getInstance().start(activity,listener,options,reqModel)
    }


    private var mListener: OnCustomStateListener? = null
    private var mState = false

    fun setListener(listener: OnCustomStateListener) {
        mListener = listener
    }

    fun changeState(state: Boolean) {
        mListener?.let {
            mState = state
            notifyStateChange(state)
        }
    }

    fun getState(): Boolean {
        return mState
    }

    private fun notifyStateChange(boolean: Boolean) {
       if(boolean) {
        mListener?.onPaymentSuccess("Payment Success")}else{
           mListener?.stateDeclined()
       }
    }
}
interface OnCustomStateListener {
    fun onPaymentSuccess(message:String)
    fun stateDeclined()
}