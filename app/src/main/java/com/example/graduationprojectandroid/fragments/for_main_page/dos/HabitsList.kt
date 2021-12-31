package com.example.graduationprojectandroid.fragments.for_main_page.dos


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graduationprojectandroid.databinding.FragmentHabitsListBinding
import com.example.graduationprojectandroid.fragments.for_main_page.adapters.*



/**
 * A simple [Fragment] subclass.
 * Use the [HabitsList.newInstance] factory method to
 * create an instance of this fragment.
 */
class HabitsList(
    private var listener: (h: Habit?) -> Unit
) : Fragment() {

    private lateinit var binding: FragmentHabitsListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHabitsListBinding.inflate(layoutInflater)
        return binding.root //.inflate(, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() = with(binding){
        addButton.setOnClickListener {
            listener(null)
        }

        val habitsListAdapter = HabitsAdapter(getHabits(), listener)

        habitsList.layoutManager = LinearLayoutManager(habitsList.context)
        habitsList.adapter = habitsListAdapter
    }



    private fun getHabits() : ArrayList<Habit>{
        val habits: ArrayList<Habit> = ArrayList()
        //TODO: Make normal habits load
        //--------------------------------------------
        //TMP

        val habit1 = Habit(
            1,
            "Бегать по утрам",
            "С 6:30 до 7:00, не забыть разминку перед бегом и после",
            HabitDoneStates.UNKNOWN
        )

        val habit2 = Habit(
            2,
            "Учить английский",
            "По средам урок в 18:00",
            HabitDoneStates.UNKNOWN
        )

        val habit3 = Habit(
            3,
            "Бросить пить",
            "Убиваем зеленаго змия весело и с пользой: во вторник встреча",
            HabitDoneStates.UNKNOWN
        )
        val habit4 = Habit(
            4,
            "Точно не йога",
            "С 6:30 до 7:00, не забыть разминку перед бегом и после",
            HabitDoneStates.UNKNOWN
        )

        val habit5 = Habit(
            5,
            "Учить немецкий",
            "По средам урок в 18:00",
            HabitDoneStates.UNKNOWN
        )

        val habit6 = Habit(
            6,
            "Бросить курить",
            "Убиваем зеленаго змия весело и с пользой: во вторник встреча",
            HabitDoneStates.UNKNOWN
        )

        val habit7 = Habit(
            7,
            "Спать",
            "С 6:30 до 7:00, не забыть разминку перед бегом и после",
            HabitDoneStates.UNKNOWN
        )

        val habit8 = Habit(
            8,
            "Учить французский",
            "По средам урок в 18:00",
            HabitDoneStates.UNKNOWN
        )

        val habit9 = Habit(
            9,
            "Бросить есть сладкое",
            "Убиваем зеленаго змия весело и с пользой: во вторник встреча",
            HabitDoneStates.UNKNOWN
        )

        val habit10 = Habit(
            10,
            "2 Бегать по утрам",
            "С 6:30 до 7:00, не забыть разминку перед бегом и после",
            HabitDoneStates.UNKNOWN
        )

        val habit11 = Habit(
            11,
            "2 Учить английский",
            "По средам урок в 18:00",
            HabitDoneStates.UNKNOWN
        )

        val habit12 = Habit(
            12,
            "2 Бросить пить",
            "Убиваем зеленаго змия весело и с пользой: во вторник встреча",
            HabitDoneStates.UNKNOWN
        )
        val habit13 = Habit(
            13,
            "2 Точно не йога",
            "С 6:30 до 7:00, не забыть разминку перед бегом и после",
            HabitDoneStates.UNKNOWN
        )

        val habit14 = Habit(
            14,
            "2 Учить немецкий",
            "По средам урок в 18:00",
            HabitDoneStates.UNKNOWN
        )

        val habit15 = Habit(
            15,
            "2 Бросить курить",
            "Убиваем зеленаго змия весело и с пользой: во вторник встреча",
            HabitDoneStates.UNKNOWN
        )

        val habit16 = Habit(
            16,
            "2 Спать",
            "С 6:30 до 7:00, не забыть разминку перед бегом и после",
            HabitDoneStates.UNKNOWN
        )

        val habit17 = Habit(
            17,
            "2 Учить французский",
            "По средам урок в 18:00",
            HabitDoneStates.UNKNOWN
        )

        val habit18 = Habit(
            18,
            "2 Бросить есть сладкое",
            "Убиваем зеленаго змия весело и с пользой: во вторник встреча",
            HabitDoneStates.UNKNOWN
        )


        habits.add(habit1)
        habits.add(habit2)
        habits.add(habit3)
        habits.add(habit4)
        habits.add(habit5)
        habits.add(habit6)
        habits.add(habit7)
        habits.add(habit8)
        habits.add(habit9)
        habits.add(habit10)
        habits.add(habit11)
        habits.add(habit12)
        habits.add(habit13)
        habits.add(habit14)
        habits.add(habit15)
        habits.add(habit16)
        habits.add(habit17)
        habits.add(habit18)

        //TMP
        //----------------------------------------------
        val filler_habit1 = Habit(
            -1,
            "",
            "",
            HabitDoneStates.UNKNOWN,
            View.INVISIBLE
        )
        val filler_habit2 = Habit(
            -1,
            "",
            "",
            HabitDoneStates.UNKNOWN,
            View.INVISIBLE
        )
        val filler_habit3 = Habit(
            -1,
            "",
            "",
            HabitDoneStates.UNKNOWN,
            View.INVISIBLE
        )
        val filler_habit4 = Habit(
            -1,
            "",
            "",
            HabitDoneStates.UNKNOWN,
            View.INVISIBLE
        )

        habits.add(filler_habit1)
        habits.add(filler_habit2)
        habits.add(filler_habit3)
        habits.add(filler_habit4)
        return habits
    }

    companion object {
        @JvmStatic
        fun newInstance(listener: (h: Habit?) -> Unit) =
            HabitsList(listener)
    }
}