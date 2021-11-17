package com.example.graduationprojectandroid


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit



/**
 * A simple [Fragment] subclass.
 * Use the [GetCode.newInstance] factory method to
 * create an instance of this fragment.
 */
class GetCode(private var listener: () -> Unit) : Fragment(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_get_code, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentManager?.commit {
            val input: Input = Input.newInstance(
                getString(R.string.code)
            )
            add(R.id.input, input)

            val button: Button = Button.newInstance(
                getString(R.string.button_text_login_1)
            ) {
                listener()
            }
            add(R.id.button, button)
        }

    }

    companion object {

        @JvmStatic
        fun newInstance(listener: () -> Unit) =
            GetCode(listener)
    }
}