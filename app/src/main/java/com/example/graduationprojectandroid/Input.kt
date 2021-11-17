package com.example.graduationprojectandroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_TIP = "tip"

/**
 * A simple [Fragment] subclass.
 * Use the [Input.newInstance] factory method to
 * create an instance of this fragment.
 */
class Input: Fragment() {
    // TODO: Rename and change types of parameters
    private var hint: String? = null
    private var editText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            hint = it.getString(ARG_TIP)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_input, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editText = view.findViewById<EditText>(R.id.input_text)
        editText?.hint = hint
    }

    public fun getText() : String {
        return editText?.text.toString()
    }

    companion object {
        @JvmStatic
        fun newInstance(hint: String) =
            Input().apply {
                arguments = Bundle().apply {
                    putString(ARG_TIP, hint)
                }
            }
    }
}