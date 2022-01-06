package com.example.graduationprojectandroid.fragments.for_main_page.dos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graduationprojectandroid.databinding.FragmentTasksListBinding
import com.example.graduationprojectandroid.fragments.for_main_page.adapters.*

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

        val habitsListAdapter
            = TasksAdapter(convertTasksToParentizedTasks(getTasks()), listener)

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

        task1.isEveryday = true
        task1.difficulty = Difficulty.hard


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

        task2.setFullDone(true)
        task2.difficulty = Difficulty.normal

        var subtasks3: MutableList<Subtask> = MutableList(
            0,
            {index -> Subtask(false, "")}
        )

        var task3 = Task(
            3,
            "Поесть",
            "Очень важно",
            subtasks3
        )

        task3.isEveryweek = true
        task3.difficulty = Difficulty.easy



        tasksList.add(task1)
        tasksList.add(task2)
        tasksList.add(task3)

        //TMP
        //-------------------------------------

        return tasksList
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

        val subtasks0: MutableList<Subtask> = MutableList(
            0,
            {index -> Subtask(false, "")}
        )

        for (i in 0 until EMPTIES){
            pTasksList.add(ParentizedTask(
                -1,
                "",
                "",
                subtasks0,
                View.GONE,
                View.GONE
            ))
        }

        return pTasksList
    }

    companion object {
        public val EMPTIES = 4

        @JvmStatic
        fun newInstance(listener: (Task?) -> Unit) =
            TasksList(listener)
    }
}