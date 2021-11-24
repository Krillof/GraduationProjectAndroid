package com.example.graduationprojectandroid.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
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
 * Use the [DescriptionSuperDesignInput.newInstance] factory method to
 * create an instance of this fragment.
 */
class DescriptionSuperDesignInput : Fragment() {
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
        return inflater.inflate(R.layout.fragment_description_super_design_input, container, false)
    }

    class ThreeLinesTextWatcher(input_text: EditText) : TextWatcher {
        val input_text = input_text
        var lastSpecialRequestsCursorPosition: Int = 0
        var specialRequests: String = ""
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            lastSpecialRequestsCursorPosition = input_text.getSelectionStart()
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            input_text.removeTextChangedListener(this);

            if (input_text.getLineCount() > 3) {
                input_text.setText(specialRequests);
                input_text.setSelection(lastSpecialRequestsCursorPosition);
            }
            else
                specialRequests = input_text.getText().toString();

            input_text.addTextChangedListener(this);
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val hiding_text: TextView = view.findViewById(R.id.hiding_text)
        val header_text: TextView = view.findViewById(R.id.small_header_text)
        val white_rectangle: View = view.findViewById(R.id.white_rectangle)
        val input_text: EditText = view.findViewById(R.id.input_text)

        hiding_text.text = header
        header_text.text = header

        input_text.addTextChangedListener(ThreeLinesTextWatcher(input_text))

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
            DescriptionSuperDesignInput().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM_HEADER, header)
                }
            }
    }
}