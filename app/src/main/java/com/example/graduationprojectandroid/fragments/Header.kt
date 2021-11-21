package com.example.graduationprojectandroid.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.commit
import com.example.graduationprojectandroid.R


private const val ARG_PARAM_TEXT = "text"

/**
 * A simple [Fragment] subclass.
 * Use the [Header.newInstance] factory method to
 * create an instance of this fragment.
 */
class Header : Fragment() {
    // TODO: Rename and change types of parameters
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_header, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val header_text = view.findViewById<TextView>(R.id.header_text)
        header_text.text = text
    }

    companion object {
        @JvmStatic
        fun newInstance(text: String) =
            Header().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM_TEXT, text)
                }
            }
    }
}