package com.example.graduationprojectandroid.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.commit
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.fragments.*
import com.example.graduationprojectandroid.fragments.for_main_page.adapters.Difficulty
import com.example.graduationprojectandroid.fragments.for_main_page.adapters.Habit
import com.example.graduationprojectandroid.fragments.for_main_page.adapters.HabitDoneStates

class CreatingHabit : AppCompatActivity() {


    private fun changeToDifficulty(diff: Difficulty){
        val switches = arrayListOf<Boolean>(false, false, false, false)
        switches[diff.type] = true
        val simple_choice = findViewById<View>(R.id.simple_difficulty_radiobutton)
        val easy_choice = findViewById<View>(R.id.easy_difficulty_radiobutton)
        val normal_choice = findViewById<View>(R.id.normal_difficulty_radiobutton)
        val hard_choice = findViewById<View>(R.id.hard_difficulty_radiobutton)

        simple_choice.setBackgroundResource(
            if (switches[Difficulty.simple.type])
                R.drawable.simple_on
            else
                R.drawable.simple_off
        )

        easy_choice.setBackgroundResource(
            if (switches[Difficulty.easy.type])
                R.drawable.easy_on
            else
                R.drawable.easy_off
        )

        normal_choice.setBackgroundResource(
            if (switches[Difficulty.normal.type])
                R.drawable.normal_on
            else
                R.drawable.normal_off
        )

        hard_choice.setBackgroundResource(
            if (switches[Difficulty.hard.type])
                R.drawable.hard_on
            else
                R.drawable.hard_off
        )

    }

    private fun getNewId(): Int {
        //TODO: get id from server
        return 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creating_habit)
        val context = this

        var gotten_habit: Habit? = intent.extras?.get(MainPage.ARG_HABIT) as Habit?
        val isNew = (gotten_habit == null)

        val good_habit_check_box_touch_area = findViewById<View>(R.id.good_habit_checkbox_touch_area)
        val bad_habit_check_box_touch_area = findViewById<View>(R.id.bad_habit_checkbox_touch_area)
        val good_habit_check_box_pic = findViewById<View>(R.id.good_habit_checkbox_pic)
        val bad_habit_check_box_pic = findViewById<View>(R.id.bad_habit_checkbox_pic)

        var habit: Habit
        = if (isNew)
            Habit(getNewId(), "", "", HabitDoneStates.UNKNOWN)
        else
            gotten_habit!!




        good_habit_check_box_touch_area.setOnClickListener {
            good_habit_check_box_pic.setBackgroundResource(R.drawable.good_habit_on)
            bad_habit_check_box_pic.setBackgroundResource(R.drawable.bad_habit_off)

            habit.isHabitGood = true
        }

        bad_habit_check_box_touch_area.setOnClickListener {
            good_habit_check_box_pic.setBackgroundResource(R.drawable.good_habit_off)
            bad_habit_check_box_pic.setBackgroundResource(R.drawable.bad_habit_on)

            habit.isHabitGood = false
        }

        good_habit_check_box_touch_area.performClick()


        val simple_choice = findViewById<View>(R.id.simple_difficulty_radiobutton)
        val easy_choice = findViewById<View>(R.id.easy_difficulty_radiobutton)
        val normal_choice = findViewById<View>(R.id.normal_difficulty_radiobutton)
        val hard_choice = findViewById<View>(R.id.hard_difficulty_radiobutton)

        simple_choice.setOnClickListener {
            habit.difficulty = Difficulty.simple
            context.changeToDifficulty(Difficulty.simple)
        }
        easy_choice.setOnClickListener {
            habit.difficulty = Difficulty.easy
            context.changeToDifficulty(Difficulty.easy)
        }
        normal_choice.setOnClickListener {
            habit.difficulty = Difficulty.normal
            context.changeToDifficulty(Difficulty.normal)
        }
        hard_choice.setOnClickListener {
            habit.difficulty = Difficulty.hard
            context.changeToDifficulty(Difficulty.hard)
        }

        normal_choice.performClick()

        val exit_button: View = findViewById(R.id.exit_button)
        val intent = Intent(this, MainPage::class.java)

        exit_button.setOnClickListener {
            if (isNew){
                //TODO: Send to server
                startActivity(intent)
                finish()
            } else {
                //Ask, before leave
                supportFragmentManager.commit{
                    val save_changes_dialogue = SaveChangesDialogue.newInstance {
                        when (it) {
                            true -> {
                                //TODO: Send to server
                                startActivity(intent)
                                finish()
                            }
                            false -> {
                                startActivity(intent)
                                finish()
                            }
                            null -> {
                                val gray_dialogue_element
                                    = findViewById<View>(R.id.gray_dialogue)
                                gray_dialogue_element.visibility = View.GONE
                            }
                        }
                    }
                    val gray_back_screen_fragment = GrayBackScreenFragment.newInstance(
                        save_changes_dialogue
                    )
                    add(R.id.gray_dialogue, gray_back_screen_fragment)
                    val gray_dialogue_element
                            = findViewById<View>(R.id.gray_dialogue)
                    gray_dialogue_element.visibility = View.VISIBLE
                }
            }
        }


        supportFragmentManager.commit {

            val header: Header = Header.newInstance(
                getString(R.string.creating_habit)
            )
            add(R.id.header, header)

            val input1: SuperDesignInput = SuperDesignInput.newInstance(
                getString(R.string.habit_name),
                habit.header
            ){
                habit.header = it
            }
            add(R.id.super_design_input, input1)

            val description_input1: DescriptionSuperDesignInput =
                DescriptionSuperDesignInput.newInstance(
                    getString(R.string.notes)
                )
            add(R.id.description_super_design_input, description_input1)






            val everyday_checkbox: BigRectangleCheckBox
            = BigRectangleCheckBox.newInstance(
                getString(R.string.everyday),
                habit.isEveryday
            ){
                habit.isEveryday = it
            }
            add(R.id.everyday_checkbox, everyday_checkbox)

            val everyweek_checkbox: BigRectangleCheckBox
            = BigRectangleCheckBox.newInstance(
                getString(R.string.everyweek),
                habit.isEveryweek
            ){
                habit.isEveryweek = it
            }
            add(R.id.everyweek_checkbox, everyweek_checkbox)

            val everymonth_checkbox: BigRectangleCheckBox
            = BigRectangleCheckBox.newInstance(
                getString(R.string.everymonth),
                habit.isEverymonth
            ){
                habit.isEverymonth = it
            }
            add(R.id.everymonth_checkbox, everymonth_checkbox)



            val checkbox: CheckBox = CheckBox.newInstance(
                getString(R.string.mark_as_done),
                habit.done == HabitDoneStates.DONE
            ){
                habit.done = if (it)  HabitDoneStates.DONE
                        else HabitDoneStates.UNKNOWN
            }
            add(R.id.checkbox, checkbox)

            val button: Button = Button.newInstance(getString(R.string.save)){
                //TODO: Send to server
                startActivity(intent)
                finish()
            }
            add(R.id.button_confirm, button)
        }
    }
}