package com.me.booksdemoandroid.feature.auth.frag


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.me.booksdemoandroid.R
import com.me.booksdemoandroid.feature.auth.vm.AuthViewModel
import com.me.booksdemoandroid.shared.extension.getViewModel
import com.me.booksdemoandroid.shared.fragment.BaseFragment
import com.me.booksdemoandroid.shared.listner.OnBackPressedListener
import com.wajahatkarim3.easyvalidation.core.collection_ktx.minLengthList
import com.wajahatkarim3.easyvalidation.core.collection_ktx.nonEmptyList
import com.wajahatkarim3.easyvalidation.core.collection_ktx.validEmailList
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : BaseFragment() {

    private var onBackPressedListener: OnBackPressedListener? = null


    val vm: AuthViewModel by lazy {
        getViewModel { AuthViewModel() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }

        vm.loading.observe(this, Observer {
            if (it) {

            } else {

            }
        })
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
            editTextPassword
        ) { view, msg ->
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

        vm.signUp(
            editTextUserName.text.trim().toString(),
            editTextMail.text.trim().toString(),
            editTextPassword.text.trim().toString()
        ).observe(this,
            Observer {

                if (it.first) {
                    onBackPressedListener?.doBack()
                }

            })

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
