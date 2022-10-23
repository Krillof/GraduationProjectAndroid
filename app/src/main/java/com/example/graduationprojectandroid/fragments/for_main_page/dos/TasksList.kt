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
import com.example.graduationprojectandroid.data.Items.ParentizedTask
import com.example.graduationprojectandroid.data.Items.Task

/**
 * A simple [Fragment] subclass.
 * Use the [TasksList.newInstance] factory method to
 * create an instance of this fragment.
 */
class TasksList(
    private var loginFrom: String,
    private var loginTo: String,
    private var hideAddButton: Boolean = false,
    private var listener: (Task?) -> Unit
) : Fragment() {
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

    private fun updateList(){
        val context = this
        DataService.getTasks(loginFrom, loginTo){
            binding.tasksList.layoutManager = LinearLayoutManager(
                binding.tasksList.context
            )
            binding.tasksList.adapter = TasksAdapter(convertTasksToParentizedTasks(it))
            { task ->
                listener(task)
                context.updateList()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        if (hideAddButton){
            addButton.visibility = View.GONE
            addButtonBackground.visibility = View.GONE
            addButtonPlus.visibility = View.GONE
        } else {
            addButton.setOnClickListener {
                listener(null)
            }
        }

        updateList()
    }

    private fun convertTasksToParentizedTasks(tasks: ArrayList<Task>)
    : ArrayList<ParentizedTask>{
        val pTasksList = ArrayList<ParentizedTask>()

        tasks.forEach {
            pTasksList.add(
                ParentizedTask(
                    it.id,
                    it.loginFrom,
                    it.loginTo,
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
        fun newInstance(loginFrom: String, loginTo: String,
                        hideAddButton: Boolean = false, listener: (Task?) -> Unit) =
            TasksList(loginFrom, loginTo, hideAddButton, listener)
    }
}