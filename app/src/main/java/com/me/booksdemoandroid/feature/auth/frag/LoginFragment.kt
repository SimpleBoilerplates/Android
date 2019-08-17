package com.me.booksdemoandroid.feature.auth.frag

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.me.booksdemoandroid.R
import com.me.booksdemoandroid.feature.auth.vm.AuthViewModel
import com.me.booksdemoandroid.feature.home.helper.NavHome
import com.me.booksdemoandroid.shared.extension.getViewModel
import com.me.booksdemoandroid.shared.extension.showSnackBar
import com.me.booksdemoandroid.shared.fragment.BaseFragment
import com.me.booksdemoandroid.shared.helper.PreferenceHelper
import com.me.booksdemoandroid.shared.helper.PreferenceHelper.set
import com.me.booksdemoandroid.shared.k.KEnum
import com.me.booksdemoandroid.shared.listner.OnBackPressedListener
import com.me.booksdemoandroid.shared.pref.Pref
import com.me.booksdemoandroid.shared.repositories.Status
import com.wajahatkarim3.easyvalidation.core.collection_ktx.minLengthList
import com.wajahatkarim3.easyvalidation.core.collection_ktx.nonEmptyList
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : BaseFragment() {

    private var onBackPressedListener: OnBackPressedListener? = null

    private val vm: AuthViewModel by lazy {
        getViewModel { AuthViewModel() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity!!.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        arguments?.let {

        }
        setUI()
        setVM()
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

    private fun setVM() {

//        vm.isLoading.observe(this, Observer {
//            if (it) {
//
//            } else {
//
//            }
//        })

        vm.result.observe(this, Observer {

            when (it?.status) {
                Status.LOADING -> {

                }
                Status.SUCCESS -> {
                    it.data?.let { it ->
                        Pref.token = it
                        val prefs = PreferenceHelper.defaultPrefs(context!!)
                        prefs[KEnum.Companion.SharedPref.Token.name] = it
                        context!!.startActivity(NavHome.showHomeActivity(context!!))
                        activity!!.finish()
                    }
                }
                Status.ERROR -> {
                    it.message?.let { message ->
                        container.showSnackBar(message, Snackbar.LENGTH_SHORT)
                    }

                }
            }

        })
    }

    private fun login() {

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
        vm.login(editTextUserName.text.trim().toString(), editTextPassword.text.trim().toString())
    }


    companion object {
        @JvmStatic
        fun newInstance(instance: Int): LoginFragment {
            val args = Bundle()
            args.putInt(ARGS_INSTANCE, instance)
            val fragment = LoginFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
