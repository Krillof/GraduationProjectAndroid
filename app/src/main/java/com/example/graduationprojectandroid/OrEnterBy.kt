package com.example.graduationprojectandroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit


/**
 * A simple [Fragment] subclass.
 * Use the [OrEnterBy.newInstance] factory method to
 * create an instance of this fragment.
 */
class OrEnterBy(private var listener: () -> Unit) : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_or_enter_by, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentManager?.commit{
            val header: Header = Header.newInstance(
                getString(R.string.or_enter_by_header)
            )
            add(R.id.header_or_enter_by, header)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(listener: () -> Unit) =
            OrEnterBy(listener)
    }
}