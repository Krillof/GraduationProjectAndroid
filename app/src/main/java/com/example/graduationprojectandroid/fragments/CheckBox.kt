package com.example.graduationprojectandroid.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import com.example.graduationprojectandroid.R


private const val ARG_PARAM_TEXT = "text"

/**
 * A simple [Fragment] subclass.
 * Use the [CheckBox.newInstance] factory method to
 * create an instance of this fragment.
 */
class CheckBox : Fragment() {
    // TODO: Rename and change types of parameters
    private var text: String? = null

    private var checked: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            text = it.getString(ARG_PARAM_TEXT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_check_box, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val checkbox_text = view.findViewById<TextView>(R.id.checkbox_text)
        checkbox_text.text = text

        val square_checkbox = view.findViewById<View>(R.id.square_checkbox)
        val full_checkbox = view.findViewById<FrameLayout>(R.id.full_checkbox)
        full_checkbox.setOnClickListener {
            checked = !checked
            square_checkbox.setBackgroundResource(
                if (checked)  R.drawable.square_checked else R.drawable.square_unchecked
            )
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(text: String) =
            CheckBox().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM_TEXT, text)
                }
            }
    }
}