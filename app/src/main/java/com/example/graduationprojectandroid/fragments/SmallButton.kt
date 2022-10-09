package com.example.graduationprojectandroid.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginLeft
import androidx.core.view.marginStart
import com.example.graduationprojectandroid.App
import com.example.graduationprojectandroid.R


private const val ARG_PARAM_TEXT = "text"

class SmallButton(private var listener: () -> Unit) : Fragment() {
    private var text: String? = null

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
        return inflater.inflate(R.layout.fragment_small_button, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button_text = view.findViewById<TextView>(R.id.button_text)
        val layout = view.findViewById<ConstraintLayout>(R.id.layout)
        button_text.text = text
        button_text.measure(0,0)

        val button_frame = view.findViewById<View>(R.id.button_frame)
        button_frame.layoutParams.width = button_frame.layoutParams.width + button_text.measuredWidth + 7

        button_frame.setOnClickListener{
            listener()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(text: String, listener: () -> Unit) =
            SmallButton(listener).apply{
                arguments = Bundle().apply {
                    putString(ARG_PARAM_TEXT, text)
                }
            }
    }
}