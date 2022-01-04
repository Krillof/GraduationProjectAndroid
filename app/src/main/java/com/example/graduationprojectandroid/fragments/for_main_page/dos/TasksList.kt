package com.example.graduationprojectandroid.fragments.for_main_page.dos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graduationprojectandroid.databinding.FragmentTasksListBinding
import com.example.graduationprojectandroid.fragments.for_main_page.adapters.Subtask
import com.example.graduationprojectandroid.fragments.for_main_page.adapters.Task
import com.example.graduationprojectandroid.fragments.for_main_page.adapters.TasksAdapter

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

        val habitsListAdapter = TasksAdapter(getTasks(), listener)

        tasksList.layoutManager = LinearLayoutManager(tasksList.context)
        tasksList.adapter = habitsListAdapter
    }

    private fun getTasks(): ArrayList<Task>{
        var tasksList = ArrayList<Task>()

        //---------------------------------------
        //TMP
        //val task1 =

        val sbtsks_strs1 = listOf("в тетради", "потом выучи текст", "приготовь картошку")

        var subtasks1: MutableList<Subtask> = MutableList(
            3,
            {index -> Subtask(false, sbtsks_strs1[index])}
        )

        var task1 = Task(
            1,
            "Сделать англ",
            "Очень надо, у меня не зачёт",
            subtasks1
        )

        val sbtsks_strs2 = listOf(
            "подумать о картошке",
            "купить картошку",
            "приготовить картошку",
            "сделать из неё торт",
            "подумать о картошке 2",
            "купить картошку 2",
            "приготовить картошку 2",
            "сделать из неё торт 2"
        )

        var subtasks2: MutableList<Subtask> = MutableList(
            sbtsks_strs2.size,
            {index -> Subtask(false, sbtsks_strs2[index])}
        )

        var task2 = Task(
            2,
            "Приготовиться к гостям",
            "Гости-то придут",
            subtasks2
        )


        var subtasks3: MutableList<Subtask> = MutableList(
            0,
            {index -> Subtask(false, "")}
        )

        var task3 = Task(
            3,
            "Поесть",
            "Очень важно",
            subtasks3,
            View.VISIBLE,
            View.GONE //because there is 0 subtasks
        )



        tasksList.add(task1)
        tasksList.add(task2)
        tasksList.add(task3)

        //TMP
        //-------------------------------------

        var subtasks0: MutableList<Subtask> = MutableList(
            0,
            {index -> Subtask(false, "")}
        )

        var task0 = Task(
            -1,
            "",
            "",
            subtasks0,
            View.GONE,
            View.GONE
        )

        var task00 = Task(
            -2,
            "",
            "",
            subtasks0,
            View.GONE,
            View.GONE
        )

        var task000 = Task(
            -3,
            "",
            "",
            subtasks0,
            View.GONE,
            View.GONE
        )

        var task0000 = Task(
            -4,
            "",
            "",
            subtasks0,
            View.GONE,
            View.GONE
        )
        tasksList.add(task0)
        tasksList.add(task00)
        tasksList.add(task000)
        tasksList.add(task0000)
        return tasksList
    }

    companion object {
        @JvmStatic
        fun newInstance(listener: (Task?) -> Unit) =
            TasksList(listener)
    }
}