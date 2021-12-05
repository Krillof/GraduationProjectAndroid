package com.example.graduationprojectandroid.fragments.for_main_page.dos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graduationprojectandroid.databinding.FragmentTasksListBinding
import com.example.graduationprojectandroid.fragments.for_main_page.adapters.Task
import com.example.graduationprojectandroid.fragments.for_main_page.adapters.TasksAdapter

/**
 * A simple [Fragment] subclass.
 * Use the [TasksList.newInstance] factory method to
 * create an instance of this fragment.
 */
class TasksList(private var listener: () -> Unit) : Fragment() {
    lateinit var binding: FragmentTasksListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTasksListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() = with(binding){
        addButton.setOnClickListener {
            listener()
        }

        val habitsListAdapter = TasksAdapter(getTasks())

        tasksList.layoutManager = LinearLayoutManager(tasksList.context)
        tasksList.adapter = habitsListAdapter

    }

    private fun getTasks(): ArrayList<Task>{
        var tasksList = ArrayList<Task>()

        //---------------------------------------
        //TMP
        //val task1 =


        //TMP
        //-------------------------------------
        return tasksList
    }

    companion object {
        @JvmStatic
        fun newInstance(listener: () -> Unit) =
            TasksList(listener)
    }
}