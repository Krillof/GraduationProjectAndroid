package com.example.graduationprojectandroid.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.graduationprojectandroid.R

private const val ARG_PARAM_TEXT = "text"

/**
 * A simple [Fragment] subclass.
 * Use the [BigRectangleCheckBox.newInstance] factory method to
 * create an instance of this fragment.
 */
class BigRectangleCheckBox(
    private val startValue: Boolean,
    private val listener: (value: Boolean)->Unit
) : Fragment() {
    private var text: String? = null

    private var checked: Boolean = startValue

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
        return inflater.inflate(R.layout.fragment_big_rectangle_check_box, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rectangle: View = view.findViewById(R.id.blue_rectangle)
        rectangle.setOnClickListener {
            checked = !checked

            listener(checked)

            rectangle.setBackgroundResource(
                if (checked)
                    R.drawable.blue_normal_rectangle_for_checkbox_on
                else
                    R.drawable.blue_normal_rectangle_for_checkbox_off
            )
        }

        rectangle.setBackgroundResource(
            if (checked)
                R.drawable.blue_normal_rectangle_for_checkbox_on
            else
                R.drawable.blue_normal_rectangle_for_checkbox_off
        )

        val text_view: TextView = view.findViewById(R.id.text)
        text_view.text = text
    }

    public fun isChecked() : Boolean = checked

    companion object {
        @JvmStatic
        fun newInstance(
            text: String,
            startValue: Boolean = false,
            listener: (value: Boolean)->Unit = {}
        ) =
            BigRectangleCheckBox(startValue, listener).apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM_TEXT, text)
                }
            }
    }
}