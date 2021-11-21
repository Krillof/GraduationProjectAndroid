package com.example.graduationprojectandroid.fragments.for_main_page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.commit
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.fragments.Header

private const val ARG_PARAM_ID = "id"
private const val ARG_PARAM_HEADER = "header"
private const val ARG_PARAM_TEXT = "text"
private const val ARG_PARAM_DONE = "done"


/**
 * A simple [Fragment] subclass.
 * Use the [OneHabit.newInstance] factory method to
 * create an instance of this fragment.
 */
class OneHabit : Fragment() {
    private var id: Int? = null
    private var header: String? = null
    private var text: String? = null
    private var done: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getInt(ARG_PARAM_ID)
            header = it.getString(ARG_PARAM_HEADER)
            text = it.getString(ARG_PARAM_TEXT)
            done = it.getInt(ARG_PARAM_DONE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_one_habit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val text = view.findViewById<TextView>(R.id.text)
        val context = this

        fragmentManager?.commit {
            val header = Header.newInstance(
                if (context.header == null) "" else context.header as String
            )
            add(R.id.header, header)
        }

        text.text = this.text
    }

    companion object {
        @JvmStatic
        fun newInstance(id: Int, header: String, text: String, done: Int) =
            OneHabit().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM_ID, id)
                    putString(ARG_PARAM_HEADER, header)
                    putString(ARG_PARAM_TEXT, text)
                    putInt(ARG_PARAM_DONE, done)
                }
            }
    }
}