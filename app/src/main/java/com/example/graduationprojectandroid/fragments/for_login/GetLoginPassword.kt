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


class GetLoginPassword(private var listener: () -> Unit) : Fragment() {


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
                getString(R.string.email)
            )
            add(R.id.input_1, input1) // Может быть, что id берётся для другого. Берегись и проверяй!

            val input2: Input = Input.newInstance(
                getString(R.string.password)
            )
            add(R.id.input_2, input2)

            val checkBox: CheckBox = CheckBox.newInstance(
                getString(R.string.login_checkbox_text)
            )
            add(R.id.checkbox, checkBox)

            val button: Button = Button.newInstance(
                getString(R.string.button_text_login_1)
            ) {
                Log.println(Log.INFO, "login: ", input1.getText())
                Log.println(Log.INFO, "password: ", input2.getText())
                listener()
            }
            add(R.id.button, button)


        }

    }

    companion object {
        @JvmStatic
        fun newInstance(listener: () -> Unit) =
            GetLoginPassword(listener)
    }

}