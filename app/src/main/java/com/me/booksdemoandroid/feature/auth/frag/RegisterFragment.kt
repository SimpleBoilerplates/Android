package com.me.booksdemoandroid.feature.auth.frag


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.me.booksdemoandroid.R
import com.me.booksdemoandroid.shared.fragment.BaseFragment
import com.me.booksdemoandroid.shared.k.KEnum
import com.me.booksdemoandroid.shared.listner.OnBackPressedListener
import com.wajahatkarim3.easyvalidation.core.collection_ktx.minLengthList
import com.wajahatkarim3.easyvalidation.core.collection_ktx.nonEmptyList
import com.wajahatkarim3.easyvalidation.core.collection_ktx.validEmailList
import kotlinx.android.synthetic.main.fragment_register.*
import org.json.JSONObject

class RegisterFragment : BaseFragment() {

    private var onBackPressedListener: OnBackPressedListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnBackPressedListener) {
            onBackPressedListener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        onBackPressedListener = null
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setUI()
    }

    private fun setUI() {

        btnRegister.setOnClickListener {
            register()
        }

        btnLogin.setOnClickListener {
            onBackPressedListener?.doBackWithStart()
        }
    }


    private fun register() {

        var isValid = true


        nonEmptyList(editTextUserName, editTextMail, editTextPassword) { view, msg ->
            view.error = msg
            isValid = false

        }

        minLengthList(
            5,
            editTextUserName,
            editTextMail,
            editTextPassword) { view, msg ->
            view.error = msg
            isValid = false
        }


        minLengthList(
            6,
            editTextPassword
        ) { view, msg ->
            view.error = msg
            isValid = false
        }


        validEmailList(editTextMail) { view, msg ->
            view.error = msg
            isValid = false
        }

//        numberEqualToList(11, editTextICNumber) { view, msg ->
//            view.error = msg
//            isValid = false
//        }
        if (!isValid) {
            return
        }

//        registerApi(
//            editTextMail.text.trim().toString(),
//            editTextPassword.text.trim().toString()
//        )
    }

    private fun registerApi(name: String, msisdn: String, icNumber: String, email: String, passWord: String) {

//        val lottieFragment =
//            IntentHelper.showLottiFragment(KEnum.Companion.LoadingType.Loading.value, getString(R.string.wait))
//        lottieFragment.show(activity!!.supportFragmentManager, "")

        // Ref<T> uses the WeakReference under the hood
        // val ref: Ref<RegisterFragment> = this.asReference()


    }

    private fun handleResult(jsonObject: JSONObject) {
        if (jsonObject.optBoolean("success")) {
            val data = jsonObject.optJSONObject("data")
            val icNumber = data.optString("ic_number")
            //startActivity(IntentHelper.showVerifyOTPActivity(context!!, icNumber))
            activity!!.finish()
        } else {

//            val lottieFragment = IntentHelper.showLottiFragment(KEnum.Companion.LoadingType.Error.value, message)
//            lottieFragment.show(activity!!.supportFragmentManager, "")
        }
    }

    companion object {

        fun newInstance(instance: Int): RegisterFragment {
            val args = Bundle()
            args.putInt(BaseFragment.ARGS_INSTANCE, instance)
            val fragment = RegisterFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
