package com.example.graduationprojectandroid.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.graduationprojectandroid.R
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged


private const val ARG_PARAM_HEADER = "header"

/**
 * A simple [Fragment] subclass.
 * Use the [SuperDesignInput.newInstance] factory method to
 * create an instance of this fragment.
 */
class SuperDesignInput(
    private val startValue: String,
    private val listener: (value: String)->Unit
) : Fragment() {

    private var header: String? = null
    var input_text: EditText? = null
    var wasCreated: Boolean = false

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
        input_text = view.findViewById(R.id.input_text)

        if (! wasCreated){
            input_text?.setText(startValue)
            wasCreated = true
        }

        hiding_text.text = header
        header_text.text = header

        white_rectangle.setOnClickListener{
            hiding_text.visibility = View.GONE
            header_text.visibility = View.VISIBLE
            input_text?.visibility = View.VISIBLE
            input_text?.requestFocus()
        }

        input_text?.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus && input_text?.text.toString() == ""){
                hiding_text.visibility = View.VISIBLE
                header_text.visibility = View.GONE
                input_text?.visibility = View.GONE
            }


            if (! hasFocus) {
                val imm =
                    view
                        .context
                        .getSystemService(Context.INPUT_METHOD_SERVICE)
                            as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }

        input_text?.doAfterTextChanged {
            listener(input_text?.text.toString())
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(
            header: String,
            startValue: String = "",
            listener: (value: String) -> Unit = {}
        ) =
            SuperDesignInput(startValue, listener).apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM_HEADER, header)
            }
            }
    }
}