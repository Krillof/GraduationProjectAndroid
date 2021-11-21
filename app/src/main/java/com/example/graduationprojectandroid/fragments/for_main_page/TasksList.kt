package com.example.graduationprojectandroid.fragments.for_main_page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.graduationprojectandroid.R

/**
 * A simple [Fragment] subclass.
 * Use the [TasksList.newInstance] factory method to
 * create an instance of this fragment.
 */
class TasksList : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tasks_list, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            TasksList()
    }
}