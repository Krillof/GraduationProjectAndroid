package com.example.graduationprojectandroid.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.databinding.FragmentCheckBoxBinding


private const val ARG_PARAM_TEXT = "text"

/**
 * A simple [Fragment] subclass.
 * Use the [CheckBox.newInstance] factory method to
 * create an instance of this fragment.
 */
class CheckBox(
    private val text: String,
    private var startValue: Boolean,
    private val listener: (value: Boolean)->Unit
) : Fragment() {

    private var checked: Boolean = false
    private lateinit var binding: FragmentCheckBoxBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCheckBoxBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    =with(binding){
        super.onViewCreated(view, savedInstanceState)
        checkboxText.text = text

        fullCheckbox.setOnClickListener {
            checked = !checked

            listener(checked)

            squareCheckbox.setBackgroundResource(
                if (checked)  R.drawable.square_checked else R.drawable.square_unchecked
            )
        }


        if (startValue){
            fullCheckbox.performClick()
            startValue = false
        }
    }

    fun isChecked(): Boolean{
        return checked
    }

    companion object {
        @JvmStatic
        fun newInstance(
            text: String,
            startValue: Boolean = false,
            listener: (value: Boolean) -> Unit = {}
        ) =
            CheckBox(text, startValue, listener)
    }
}