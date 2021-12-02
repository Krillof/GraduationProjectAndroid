package com.example.graduationprojectandroid.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.FragmentContainerView
import com.example.graduationprojectandroid.fragments.for_main_page.HabitsList
import com.example.graduationprojectandroid.fragments.for_main_page.PresentCharacterSmall
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.fragments.for_main_page.TasksList

class MainPage : AppCompatActivity() {

    enum class Pages{
        habits,
        tasks,
        character
    }


    //-------------------------------------------
    //TMP
    private val login: String = "Константин Константиновский"
    //TMP
    //-------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main_page)

        //val first_fragment = findViewById<FragmentContainerView>(R.id.first_fragment)
        //val second_fragment = findViewById<FragmentContainerView>(R.id.second_fragment)


        val character_menu_choice = findViewById<View>(R.id.character_menu_choice_click_rectangle)
        val habits_menu_choice = findViewById<View>(R.id.habits_menu_choice_click_rectangle)
        val tasks_menu_choice = findViewById<View>(R.id.tasks_menu_choice_click_rectangle)

        val context = this

        character_menu_choice.setOnClickListener {
            context.turnPageOnBottomMenuTo(Pages.character)




        }

        habits_menu_choice.setOnClickListener{

            //Make getting health and exp. by internet!

            context.turnPageOnBottomMenuTo(Pages.habits)

            val presentCharacterSmall
                = PresentCharacterSmall.newInstance(login, 85, 248, 13)

            val habitsList
                = HabitsList.newInstance()
            {
                startActivity(Intent(context, CreatingHabit::class.java))
                finish()
            }


            supportFragmentManager.beginTransaction()
                .replace(R.id.first_fragment, presentCharacterSmall)
                .replace(R.id.second_fragment, habitsList)
                .commit()
        }

        tasks_menu_choice.setOnClickListener {
            context.turnPageOnBottomMenuTo(Pages.tasks)

            val presentCharacterSmall
                    = PresentCharacterSmall.newInstance(login, 85, 248, 13)

            val tasksList
                    = TasksList.newInstance(){

            }


            supportFragmentManager.beginTransaction()
                .replace(R.id.first_fragment, presentCharacterSmall)
                .replace(R.id.second_fragment, tasksList)
                .commit()

        }

        habits_menu_choice.performClick()
    }

    private fun turnPageOnBottomMenuTo(page: Pages){
        val character_menu_choice = findViewById<View>(R.id.character_menu_choice)
        val habits_menu_choice = findViewById<View>(R.id.habits_menu_choice)
        val tasks_menu_choice = findViewById<View>(R.id.tasks_menu_choice)

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

        val character_menu_choice_text_off = findViewById<TextView>(R.id.main_page_character_text_off)
        val habits_menu_choice_text_off = findViewById<TextView>(R.id.main_page_habits_text_off)
        val tasks_menu_choice_text_off = findViewById<TextView>(R.id.main_page_tasks_text_off)
        val character_menu_choice_text_on = findViewById<TextView>(R.id.main_page_character_text_on)
        val habits_menu_choice_text_on = findViewById<TextView>(R.id.main_page_habits_text_on)
        val tasks_menu_choice_text_on = findViewById<TextView>(R.id.main_page_tasks_text_on)

        character_menu_choice_text_off.visibility = if (page == Pages.character) View.GONE else View.VISIBLE
        habits_menu_choice_text_off.visibility = if (page == Pages.habits) View.GONE else View.VISIBLE
        tasks_menu_choice_text_off.visibility = if (page == Pages.tasks) View.GONE else View.VISIBLE

        character_menu_choice_text_on.visibility = if (page == Pages.character) View.VISIBLE else View.GONE
        habits_menu_choice_text_on.visibility = if (page == Pages.habits) View.VISIBLE else View.GONE
        tasks_menu_choice_text_on.visibility = if (page == Pages.tasks) View.VISIBLE else View.GONE
    }


}