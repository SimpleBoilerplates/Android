package com.me.booksdemoandroid.feature.auth.frag

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.me.booksdemoandroid.App
import com.me.booksdemoandroid.R
import com.me.booksdemoandroid.shared.fragment.BaseFragment
import com.me.booksdemoandroid.shared.helper.PreferenceHelper
import com.me.booksdemoandroid.shared.k.KEnum
import com.me.booksdemoandroid.shared.listner.OnBackPressedListener
import kotlinx.android.synthetic.main.fragment_login.*
import com.wajahatkarim3.easyvalidation.core.collection_ktx.minLengthList
import com.wajahatkarim3.easyvalidation.core.collection_ktx.nonEmptyList
import org.json.JSONObject
import com.me.booksdemoandroid.shared.helper.PreferenceHelper.get
import com.me.booksdemoandroid.shared.helper.PreferenceHelper.set
import androidx.lifecycle.ViewModelProviders
import com.me.booksdemoandroid.feature.auth.vm.LoginViewModel
import com.me.booksdemoandroid.shared.extension.getViewModel


class LoginFragment : BaseFragment() {

    private var onBackPressedListener: OnBackPressedListener? = null


    val vm: LoginViewModel by lazy {
        getViewModel { LoginViewModel() }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity!!.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        arguments?.let {

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnBackPressedListener) {
            onBackPressedListener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
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
            onBackPressedListener?.doBackWithStart()
        }

        login.setOnClickListener {
            login()
        }


    }

    private fun login() {

        // startActivity(IntentHelper.showHomeActivity(context!!))

        var isValid = true

        nonEmptyList(editTextUserName, editTextPassword) { view, msg ->
            view.error = msg
            isValid = false
        }

        minLengthList(6, editTextUserName) { view, msg ->
            view.error = msg
            isValid = false
        }

        minLengthList(6, editTextPassword) { view, msg ->
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

        //TODO:- Make Call
        loginApi(editTextUserName.text.trim().toString(), editTextPassword.text.trim().toString())
    }

    private fun loginApi(icNumber: String, passWord: String) {

    }

    private fun handleResult(data: JSONObject) {
        if (data.optBoolean("success")) {
            val prefs = PreferenceHelper.defaultPrefs(context!!)
            prefs[""] = data.optJSONObject("data").optString("access_token")
            prefs[KEnum.Companion.SharedPref.ExpiresIn.name] = data.optJSONObject("data").optInt("expires_in")
            //prefs[KEnum.Companion.SharedPref.LoginTime.name] = LocalDateTime.now()
            App.isAuthenticated = true
           // startActivity(IntentHelper.showHomeActivity(context!!))
        } else {
//            val lottieFragment =
//                IntentHelper.showLottiFragment(KEnum.Companion.LoadingType.Error.value, data.optString("error"))
//            lottieFragment.show(activity!!.supportFragmentManager, "")
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(instance: Int): LoginFragment {
            val args = Bundle()
            args.putInt(BaseFragment.ARGS_INSTANCE, instance)
            val fragment = LoginFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
