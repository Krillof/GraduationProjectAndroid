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
        fragmentManager?.commit {

            val header: Header = Header.newInstance(
                getString(R.string.activity_login_header)
            )
            add(R.id.header, header)

            val input1: Input = Input.newInstance(
                getString(R.string.login),
                loginErrorMessage
            )
            add(R.id.input_1, input1) // Может быть, что id берётся для другого. Берегись и проверяй!

            val input2: Input = Input.newInstance(
                getString(R.string.password),
                passwordErrorMessage
            )
            add(R.id.input_2, input2)

            val checkBox: CheckBox = CheckBox.newInstance(
                getString(R.string.login_checkbox_text)
            )
            add(R.id.checkbox, checkBox)

            val button: Button = Button.newInstance(
                getString(R.string.next)
            ) {
                listener(input1.getText(), input2.getText(), checkBox.isChecked())
            }
            add(R.id.button, button)


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