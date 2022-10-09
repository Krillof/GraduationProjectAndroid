package com.example.graduationprojectandroid.fragments.for_main_page.dos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graduationprojectandroid.databinding.FragmentTasksListBinding
import com.example.graduationprojectandroid.fragments.for_main_page.adapters.*
import com.example.graduationprojectandroid.network.DataService

/**
 * A simple [Fragment] subclass.
 * Use the [TasksList.newInstance] factory method to
 * create an instance of this fragment.
 */
class TasksList(private var listener: (Task?) -> Unit) : Fragment() {
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)


        addButton.setOnClickListener {
            listener(null)
        }

        DataService.getTasks{
            val habitsListAdapter
                    = TasksAdapter(convertTasksToParentizedTasks(it), listener)

            tasksList.layoutManager = LinearLayoutManager(tasksList.context)
            tasksList.adapter = habitsListAdapter
        }
    }

    private fun convertTasksToParentizedTasks(tasks: ArrayList<Task>)
    : ArrayList<ParentizedTask>{
        var pTasksList = ArrayList<ParentizedTask>()

        tasks.forEach {
            pTasksList.add(
                ParentizedTask(
                    it.id,
                    it.header,
                    it.text,
                    it.getSubtasks(),
                    View.VISIBLE,
                    View.VISIBLE,
                    it.isEveryday,
                    it.isEveryweek,
                    it.isEverymonth,
                    it.difficulty
                )
            )
        }

        if (pTasksList.size > 0)
            pTasksList[pTasksList.size - 1].show_subtasks_always = View.GONE

        return pTasksList
    }

    companion object {

        @JvmStatic
        fun newInstance(listener: (Task?) -> Unit) =
            TasksList(listener)
    }
}