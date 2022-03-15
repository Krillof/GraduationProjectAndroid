package com.example.graduationprojectandroid.fragments.for_login


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.fragments.Button
import com.example.graduationprojectandroid.fragments.CheckBox
import com.example.graduationprojectandroid.fragments.Header
import com.example.graduationprojectandroid.fragments.Input


class GetLoginPassword(
    private var listener: (login: String, password: String, isRegistering: Boolean) -> Unit,
    private val loginErrorMessage: String,
    private val passwordErrorMessage: String
) : Fragment() {

    private var isRegistering = true
    private var input1: Input? = null
    private var input2: Input? = null
    private var checkBox: CheckBox? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_get_login_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        fragmentManager?.commit {

            val header: Header = Header.newInstance(
                getString(
                    if (isRegistering)
                        R.string.create_your_account
                    else
                        R.string.log_in
                )
            )
            replace(R.id.header, header)

            if (input1 == null) input1 = Input.newInstance(
                getString(R.string.login),
                loginErrorMessage
            )
            replace(R.id.input_1, input1!!) // Может быть, что id берётся для другого. Берегись и проверяй!

            if (input2 == null) input2= Input.newInstance(
                getString(R.string.password),
                passwordErrorMessage
            )
            replace(R.id.input_2, input2!!)

            if (!isRegistering) {
                if (checkBox == null) checkBox = CheckBox.newInstance(
                    getString(R.string.login_checkbox_text)
                )
                checkBox!!.view?.visibility = View.VISIBLE
                replace(R.id.checkbox, checkBox!!)
            } else {
                if (checkBox != null){
                    checkBox!!.view?.visibility = View.INVISIBLE
                }
            }

            val button1: Button = Button.newInstance(
                getString(R.string.next)
            ) {
                listener(input1!!.getText(), input2!!.getText(), isRegistering)
            }
            replace(R.id.button1, button1)

            val button2: Button = Button.newInstance(
                getString(
                    if (isRegistering)
                        R.string.i_already_have_an_account
                    else
                        R.string.i_do_not_have_an_account
                )
            ) {
                isRegistering = !isRegistering
                init()
            }
            replace(R.id.button2, button2)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(listener: (login: String, password: String, isRegistering: Boolean) -> Unit,
                        loginErrorMessage: String, passwordErrorMessage: String
        )
        =
            GetLoginPassword(listener, loginErrorMessage, passwordErrorMessage)
    }

}