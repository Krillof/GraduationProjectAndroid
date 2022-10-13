package com.example.graduationprojectandroid.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.databinding.FragmentInputBinding


/**
 * A simple [Fragment] subclass.
 * Use the [Input.newInstance] factory method to
 * create an instance of this fragment.
 */
class Input(
    private val hint: String,
    private val errorMessage: String,
    private val listener: (value: String)->Unit
): Fragment() {

    private lateinit var binding: FragmentInputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInputBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit
        = with(binding){
        super.onViewCreated(view, savedInstanceState)

        if (errorMessage != ""){
            inputFrame.setBackgroundResource(R.drawable.input_frame_error)
        }

        inputText.hint = hint
        inputText.doAfterTextChanged {
            listener(inputText.text.toString())
        }
    }

    fun getText() : String
    = with(binding){
        return inputText.text.toString()
    }

    companion object {
        @JvmStatic
        fun newInstance(hint: String, errorMessage: String = "",
                        listener: (value: String) -> Unit = {}) =
            Input(hint, errorMessage, listener)
    }
}