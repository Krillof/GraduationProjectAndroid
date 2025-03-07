package com.example.graduationprojectandroid.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.commit
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.fragments.*
import com.example.graduationprojectandroid.fragments.for_main_page.InfoDialogue
import com.example.graduationprojectandroid.data.States.Difficulty
import com.example.graduationprojectandroid.data.Items.Habit
import com.example.graduationprojectandroid.data.States.HabitDoneStates
import com.example.graduationprojectandroid.network.DataService

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

    private fun showInfoDialogue(text: String){
        if (text == ""){
            startActivity(intent)
            finish()
        } else {
            var infoDialogue: InfoDialogue? = null
            infoDialogue = InfoDialogue.newInstance(text){
                infoDialogue?.dismissAllowingStateLoss()
            }
            infoDialogue.show(supportFragmentManager, "info_dialogue")
        }
    }

    private fun closeHabit(isConfirmedClose: Boolean, isNewHabit: Boolean, habit: Habit){
        if (isConfirmedClose) {
            if (isNewHabit){
                DataService.createHabit(habit) { answer ->
                    showInfoDialogue(answer)
                    if (answer == "")
                        finish()
                }
            } else {
                DataService.editHabit(habit) { answer ->
                    showInfoDialogue(answer)
                    if (answer == "")
                        finish()
                }
            }

        } else {
            if (!isNewHabit)  {
                var df: DialogFragment? = null
                df = AskQuestionDialogue(getString(R.string.is_save_changes)) { choice ->
                    when (choice) {
                        true -> {
                            DataService.editHabit(habit) { answer ->
                                showInfoDialogue(answer)
                            }
                            finish()
                        }
                        false -> {
                            finish()
                        }
                        null -> {
                            df?.dismissAllowingStateLoss()
                        }
                    }
                }

                df.show(supportFragmentManager, "save_changes")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creating_habit)
        val context = this

        val gotten_habit: Habit? = intent.extras?.get(ARG_HABIT) as Habit?
        val loginFrom: String? = intent.extras?.get(ARG_LOGIN_FROM) as String?
        val loginTo: String? = intent.extras?.get(ARG_LOGIN_TO) as String?
        val isNew = (gotten_habit == null)


        val habit: Habit = if (isNew)
            Habit(-1, loginFrom!!, loginTo!!, "", "", HabitDoneStates.UNKNOWN)
        else
            gotten_habit!!

        val simple_choice = findViewById<View>(R.id.simple_difficulty_radiobutton)
        val easy_choice = findViewById<View>(R.id.easy_difficulty_radiobutton)
        val normal_choice = findViewById<View>(R.id.normal_difficulty_radiobutton)
        val hard_choice = findViewById<View>(R.id.hard_difficulty_radiobutton)
        val good_habit_check_box_touch_area =
            findViewById<View>(R.id.good_habit_checkbox_touch_area)
        val bad_habit_check_box_touch_area =
            findViewById<View>(R.id.bad_habit_checkbox_touch_area)
        val good_habit_check_box_pic = findViewById<View>(R.id.good_habit_checkbox_pic)
        val bad_habit_check_box_pic = findViewById<View>(R.id.bad_habit_checkbox_pic)
        val input = findViewById<View>(R.id.super_design_input)
        val description = findViewById<View>(R.id.description_super_design_input)
        val everyday_chbx = findViewById<View>(R.id.everyday_checkbox)
        val everyweek_chbx = findViewById<View>(R.id.everyweek_checkbox)
        val everymonth_chbx = findViewById<View>(R.id.everymonth_checkbox)
        val chbx = findViewById<View>(R.id.checkbox)
        val button_confirm = findViewById<View>(R.id.button_confirm)

        val exit_button: View = findViewById(R.id.exit_button)

        exit_button.setOnClickListener {
            closeHabit(false, isNew, habit)
        }

        onBackPressedDispatcher.addCallback {
            exit_button.performClick()
        }

        button_confirm.setOnClickListener {
            closeHabit(true, isNew, habit)
        }

        supportFragmentManager.commit {

            val header: Header = Header.newInstance(
                getString(R.string.creating_habit)
            )
            replace(R.id.header, header)


            val input1: SuperDesignInput = SuperDesignInput.newInstance(
                getString(R.string.habit_name),
                !isNew,
                habit.header
            ) {
                habit.header = it
            }
            replace(R.id.super_design_input, input1)


            val description_input1: DescriptionSuperDesignInput =
                DescriptionSuperDesignInput.newInstance(
                    getString(R.string.notes),
                    !isNew,
                    habit.text
                ) {
                    habit.text = it
                }
            replace(R.id.description_super_design_input, description_input1)


            val checkbox: CheckBox = CheckBox.newInstance(
                getString(R.string.mark_as_done),
                habit.done == HabitDoneStates.DONE
            ) {
                habit.done = if (it) HabitDoneStates.DONE
                else HabitDoneStates.UNKNOWN
            }
            replace(R.id.checkbox, checkbox)


            val button: Button = Button.newInstance(getString(R.string.save)) {
                //TODO: Send to server
                startActivity(intent)
                finish()
            }
            replace(R.id.button_confirm, button)
        }



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

        context.changeToDifficulty(habit.difficulty)

    }

    companion object {
        const val ARG_HABIT = "habit"
        const val ARG_LOGIN_FROM = "login_from"
        const val ARG_LOGIN_TO = "login_to"
    }
}