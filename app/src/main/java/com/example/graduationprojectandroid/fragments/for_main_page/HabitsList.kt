package com.example.graduationprojectandroid.fragments.for_main_page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.fragments.for_main_page.adapters.Habit


/**
 * A simple [Fragment] subclass.
 * Use the [HabitsList.newInstance] factory method to
 * create an instance of this fragment.
 */
class HabitsList(private var listener: () -> Unit) : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_habits_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val add_button = view.findViewById<View>(R.id.add_button)
        add_button.setOnClickListener {
            listener()
        }


        //TODO: Make normal habits load
        //--------------------------------------------
        //TMP
        val habit1 = Habit(
            1,
            "Бегать по утрам",
            "С 6:30 до 7:00, не забыть разминку перед бегом и после",
            0
        )

        val habit2 = Habit(
            2,
            "Учить английский",
            "По средам урок в 18:00",
            0
        )

        val habit3 = Habit(
            3,
            "Бросить пить",
            "Убиваем зеленаго змия весело и с пользой: во вторник встреча",
            0
        )
        //TMP
        //----------------------------------------------

        //val habitsListAdapter = HabitsAdapter(view.context, listOf(habit1, habit2, habit3))
        //val habitsListLayout = view.findViewById<ConstraintLayout>(R.id.habits_list_layout)


    }

    companion object {
        @JvmStatic
        fun newInstance(listener: () -> Unit) =
            HabitsList(listener)
    }
}