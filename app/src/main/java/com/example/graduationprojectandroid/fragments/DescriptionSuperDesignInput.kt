package com.example.graduationprojectandroid.fragments

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import com.example.graduationprojectandroid.R

private const val ARG_PARAM_HEADER = "header"

/**
 * A simple [Fragment] subclass.
 * Use the [DescriptionSuperDesignInput.newInstance] factory method to
 * create an instance of this fragment.
 */
class DescriptionSuperDesignInput(
    private val isShowingTextFromStart: Boolean,
    private val startValue: String,
    private val listener: (value: String)->Unit
) : Fragment() {
    private var header: String? = null
    private var input_text: EditText? = null
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
        return inflater.inflate(R.layout.fragment_description_super_design_input, container, false)
    }

    class ThreeLinesTextWatcher(
        private var input_text: EditText?,
        private var listener: (value: String) -> Unit
    ) : TextWatcher {
        var lastSpecialRequestsCursorPosition: Int = 0
        var specialRequests: String = ""

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            lastSpecialRequestsCursorPosition = input_text?.getSelectionStart()!!
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            input_text?.removeTextChangedListener(this);

            if (input_text?.getLineCount()!! > 3) {
                input_text?.setText(specialRequests);
                input_text?.setSelection(lastSpecialRequestsCursorPosition);
            }
            else
                specialRequests = input_text?.getText().toString();

            input_text?.addTextChangedListener(this);

            listener(input_text?.text.toString())
        }

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

        val context = this.view

        hiding_text.text = header
        header_text.text = header

        input_text?.addTextChangedListener(ThreeLinesTextWatcher(input_text, listener))

        white_rectangle.setOnClickListener{
            hiding_text.visibility = View.GONE
            header_text.visibility = View.VISIBLE
            input_text?.visibility = View.VISIBLE
            input_text?.requestFocus()
        }

        if (isShowingTextFromStart){
            hiding_text.visibility = View.GONE
            header_text.visibility = View.VISIBLE
            input_text?.visibility = View.VISIBLE
        }

        input_text?.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus && input_text?.text.toString() == ""
                && !isShowingTextFromStart){
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
    }

    public fun getText() : String {
        return if (input_text == null) "" else input_text?.text.toString()
    }

    companion object {
        @JvmStatic
        fun newInstance(
            header: String,
            isShowingTextFromStart: Boolean,
            startValue: String = "",
            listener: (value: String) -> Unit = {}
        ) =
            DescriptionSuperDesignInput(
                isShowingTextFromStart,
                startValue,
                listener).apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM_HEADER, header)
                }
            }
    }
}