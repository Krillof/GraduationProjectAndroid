package com.example.graduationprojectandroid.fragments.for_main_page.dos

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.commit
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.activities.CreatingHabit

private const val ARG_PARAM_LOGIN = "login"


class Dos(
    private val listener_open_menu: ()->Unit,
    private val listener_open_creating_habit: ()->Unit
) : Fragment() {
    private var login: String? = null

    enum class Pages{
        habits,
        tasks,
        character
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            login = it.getString(ARG_PARAM_LOGIN)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_dos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val character_menu_choice = view.findViewById<View>(R.id.character_menu_choice_click_rectangle)
        val habits_menu_choice = view.findViewById<View>(R.id.habits_menu_choice_click_rectangle)
        val tasks_menu_choice = view.findViewById<View>(R.id.tasks_menu_choice_click_rectangle)
        val open_menu_area = view.findViewById<View>(R.id.top_menu_click_area)


        val context = this

        open_menu_area.setOnClickListener {
            listener_open_menu()
        }

        character_menu_choice.setOnClickListener {
            context.turnPageOnBottomMenuTo(Pages.character, view)


            fragmentManager?.commit{
                val presentCharacterBig
                    = PresentCharacterBig.newInstance()

                val marketList
                    = MarketList.newInstance()



                replace(R.id.first_fragment, presentCharacterBig)
                replace(R.id.second_fragment, marketList)

            }

        }

        habits_menu_choice.setOnClickListener{

            //TODO: Make getting health and exp. by internet!

            context.turnPageOnBottomMenuTo(Pages.habits, view)

            fragmentManager?.commit{
                val presentCharacterSmall
                        = PresentCharacterSmall.newInstance(login!!, 85, 248, 13)

                val habitsList
                        = HabitsList.newInstance()
                {
                    listener_open_creating_habit()
                }

                replace(R.id.first_fragment, presentCharacterSmall)
                replace(R.id.second_fragment, habitsList)
            }
        }

        tasks_menu_choice.setOnClickListener {
            context.turnPageOnBottomMenuTo(Pages.tasks, view)




            fragmentManager?.commit{
                val presentCharacterSmall
                        = PresentCharacterSmall.newInstance(login!!, 85, 248, 13)

                val tasksList
                        = TasksList.newInstance(){

                }

                replace(R.id.first_fragment, presentCharacterSmall)
                replace(R.id.second_fragment, tasksList)
            }

        }

        habits_menu_choice.performClick()
    }


    private fun turnPageOnBottomMenuTo(page: Pages, view: View){
        val character_menu_choice = view.findViewById<View>(R.id.character_menu_choice)
        val habits_menu_choice = view.findViewById<View>(R.id.habits_menu_choice)
        val tasks_menu_choice = view.findViewById<View>(R.id.tasks_menu_choice)

        character_menu_choice.setBackgroundResource(
            if (page == Pages.character)
                R.drawable.bottom_menu_character_on
            else
                R.drawable.bottom_menu_character_off
        )

        habits_menu_choice.setBackgroundResource(
            if (page == Pages.habits)
                R.drawable.bottom_menu_habits_on
            else
                R.drawable.bottom_menu_habits_off
        )

        tasks_menu_choice.setBackgroundResource(
            if (page == Pages.tasks)
                R.drawable.bottom_menu_tasks_on
            else
                R.drawable.bottom_menu_tasks_off
        )

        val character_menu_choice_text_off = view.findViewById<TextView>(R.id.main_page_character_text_off)
        val habits_menu_choice_text_off = view.findViewById<TextView>(R.id.main_page_habits_text_off)
        val tasks_menu_choice_text_off = view.findViewById<TextView>(R.id.main_page_tasks_text_off)
        val character_menu_choice_text_on = view.findViewById<TextView>(R.id.main_page_character_text_on)
        val habits_menu_choice_text_on = view.findViewById<TextView>(R.id.main_page_habits_text_on)
        val tasks_menu_choice_text_on = view.findViewById<TextView>(R.id.main_page_tasks_text_on)

        character_menu_choice_text_off.visibility = if (page == Pages.character) View.GONE else View.VISIBLE
        habits_menu_choice_text_off.visibility = if (page == Pages.habits) View.GONE else View.VISIBLE
        tasks_menu_choice_text_off.visibility = if (page == Pages.tasks) View.GONE else View.VISIBLE

        character_menu_choice_text_on.visibility = if (page == Pages.character) View.VISIBLE else View.GONE
        habits_menu_choice_text_on.visibility = if (page == Pages.habits) View.VISIBLE else View.GONE
        tasks_menu_choice_text_on.visibility = if (page == Pages.tasks) View.VISIBLE else View.GONE
    }

    companion object {
        @JvmStatic
        fun newInstance(
            login: String,
            listener_open_menu: ()->Unit,
            listener_open_creating_habit: ()->Unit
        ) =
            Dos(
                listener_open_menu,
                listener_open_creating_habit
            ).apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM_LOGIN, login)
                }
            }
    }
}