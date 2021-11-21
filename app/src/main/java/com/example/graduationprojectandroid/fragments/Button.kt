package com.example.graduationprojectandroid.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.graduationprojectandroid.R


private const val ARG_PARAM_TEXT = "text"

/**
 * A simple [Fragment] subclass.
 * Use the [Button.newInstance] factory method to
 * create an instance of this fragment.
 */
class Button(private var listener: () -> Unit) : Fragment() {
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
        return inflater.inflate(R.layout.fragment_button, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button_text = view.findViewById<TextView>(R.id.button_text)
        button_text.text = text
        val button_frame = view.findViewById<ConstraintLayout>(R.id.button_frame)
        button_frame.setOnClickListener{
            listener()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(text: String, listener: () -> Unit) =
            Button(listener).apply{
                arguments = Bundle().apply {
                    putString(ARG_PARAM_TEXT, text)
                }
            }
    }
}