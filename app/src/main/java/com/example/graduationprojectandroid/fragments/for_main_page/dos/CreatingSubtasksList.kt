package com.example.graduationprojectandroid.fragments.for_main_page.dos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.databinding.FragmentCreatingSubtasksListBinding
import com.example.graduationprojectandroid.databinding.FragmentHabitsListBinding
import com.example.graduationprojectandroid.fragments.for_main_page.adapters.CreatingSubtaskAdapter
import com.example.graduationprojectandroid.fragments.for_main_page.adapters.Task

/**
 * A simple [Fragment] subclass.
 * Use the [CreatingSubtasksList.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreatingSubtasksList(
    public var task: Task
) : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    private lateinit var binding: FragmentCreatingSubtasksListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreatingSubtasksListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        creatingSubtasksList.layoutManager = LinearLayoutManager(creatingSubtasksList.context)
        creatingSubtasksList.adapter = CreatingSubtaskAdapter(task.subtasks)
    }

    companion object {

        @JvmStatic
        fun newInstance(task: Task) =
            CreatingSubtasksList(task)

    }
}