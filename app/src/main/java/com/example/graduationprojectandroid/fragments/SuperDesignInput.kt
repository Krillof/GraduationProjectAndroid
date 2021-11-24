package com.example.graduationprojectandroid.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.example.graduationprojectandroid.R

private const val ARG_PARAM_HEADER = "header"

/**
 * A simple [Fragment] subclass.
 * Use the [SuperDesignInput.newInstance] factory method to
 * create an instance of this fragment.
 */
class SuperDesignInput : Fragment() {
    private var header: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            header = it.getString(ARG_PARAM_HEADER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_super_design_input, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val hiding_text: TextView = view.findViewById(R.id.hiding_text)
        val header_text: TextView = view.findViewById(R.id.small_header_text)
        val white_rectangle: View = view.findViewById(R.id.white_rectangle)
        val input_text: EditText = view.findViewById(R.id.input_text)

        hiding_text.text = header
        header_text.text = header

        white_rectangle.setOnClickListener{
            hiding_text.visibility = View.GONE
            header_text.visibility = View.VISIBLE
            input_text.visibility = View.VISIBLE
            input_text.requestFocus()
        }

        input_text.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus && input_text.text.toString() == ""){
                hiding_text.visibility = View.VISIBLE
                header_text.visibility = View.GONE
                input_text.visibility = View.GONE
            }
        }
    }

    public fun getText() : String {
        TODO("RETURN STRING")
    }

    companion object {
        @JvmStatic
        fun newInstance(header: String) =
            SuperDesignInput().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM_HEADER, header)
            }
            }
    }
}