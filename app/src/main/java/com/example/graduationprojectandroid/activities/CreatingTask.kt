package com.example.graduationprojectandroid.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.fragments.*
import com.example.graduationprojectandroid.fragments.for_main_page.adapters.*
import com.example.graduationprojectandroid.fragments.for_main_page.dos.CreatingSubtasksList

class CreatingTask : AppCompatActivity() {

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

    private fun goBack(task: Task){
        task.getSubtasks().removeAt(task.getSubtasks().size - 1)

        //if ID alreadly exists - than change, else make new
        //TODO: Send to server
        val intent = Intent(this, MainPage::class.java)
        intent.putExtra(IS_WAS_CREATING_TASK, true)
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creating_task)
        val context = this

        val gotten_task: Task? = intent.extras?.get(MainPage.ARG_TASK) as Task?
        val isNew = (gotten_task == null)

        var task: Task
                = if (isNew)
            Task(getNewId(), "", "",
                MutableList(0, { Subtask(false, "") }))
        else
            gotten_task!!

        val simple_choice = findViewById<View>(R.id.simple_difficulty_radiobutton)
        val easy_choice = findViewById<View>(R.id.easy_difficulty_radiobutton)
        val normal_choice = findViewById<View>(R.id.normal_difficulty_radiobutton)
        val hard_choice = findViewById<View>(R.id.hard_difficulty_radiobutton)
        val input = findViewById<View>(R.id.super_design_input)
        val description = findViewById<View>(R.id.description_super_design_input)
        val creating_subtasks_list = findViewById<View>(R.id.creating_subtasks_list)
        val everyday_chbx = findViewById<View>(R.id.everyday_checkbox)
        val everyweek_chbx = findViewById<View>(R.id.everyweek_checkbox)
        val everymonth_chbx = findViewById<View>(R.id.everymonth_checkbox)
        val chbx = findViewById<View>(R.id.checkbox)
        val button_confirm = findViewById<View>(R.id.button_confirm)
        val exit_button: View = findViewById(R.id.exit_button)

        exit_button.setOnClickListener {
            if (isNew){
                goBack(task)
            } else {
                //Ask, before leave
                var df: DialogFragment? = null
                df = SaveChangesDialogue {
                    when (it) {
                        true -> {
                            goBack(task)
                        }
                        false -> {
                            goBack(task)
                        }
                        null -> {
                            df?.dismissAllowingStateLoss()
                        }
                    }
                }
                df.show(supportFragmentManager, "save_changes")
            }
        }


        supportFragmentManager.commit {

            val header: Header = Header.newInstance(
                getString(R.string.creating_habit)
            )
            replace(R.id.header, header)


            val input1: SuperDesignInput = SuperDesignInput.newInstance(
                getString(R.string.habit_name),
                !isNew,
                task.header
            ){
                task.header = it
            }
            replace(R.id.super_design_input, input1)


            val description_input1: DescriptionSuperDesignInput =
                DescriptionSuperDesignInput.newInstance(
                    getString(R.string.notes),
                    !isNew,
                    task.text
                ){
                    task.text = it
                }
            replace(R.id.description_super_design_input, description_input1)


            val everyday_checkbox: BigRectangleCheckBox
                    = BigRectangleCheckBox.newInstance(
                getString(R.string.everyday),
                task.isEveryday
            ){
                task.isEveryday = it
            }
            replace(R.id.everyday_checkbox, everyday_checkbox)


            val everyweek_checkbox: BigRectangleCheckBox
                    = BigRectangleCheckBox.newInstance(
                getString(R.string.everyweek),
                task.isEveryweek
            ){
                task.isEveryweek = it
            }
            replace(R.id.everyweek_checkbox, everyweek_checkbox)



            val everymonth_checkbox: BigRectangleCheckBox
                    = BigRectangleCheckBox.newInstance(
                getString(R.string.everymonth),
                task.isEverymonth
            ){
                task.isEverymonth = it
            }
            replace(R.id.everymonth_checkbox, everymonth_checkbox)


            task.getSubtasks().add(Subtask())
            val creating_subtasks_list: CreatingSubtasksList =
                CreatingSubtasksList.newInstance(task)
            replace(R.id.creating_subtasks_list, creating_subtasks_list)



            val checkbox: CheckBox = CheckBox.newInstance(
                getString(R.string.mark_as_done),
                task.isDone()
            ){
                task.setFullDone(true)
            }
            replace(R.id.checkbox, checkbox)



            val button: Button = Button.newInstance(getString(R.string.save)){
                goBack(task)
            }
            replace(R.id.button_confirm, button)
        }



        simple_choice.setOnClickListener {

            task.difficulty = Difficulty.simple
            context.changeToDifficulty(Difficulty.simple)

        }
        easy_choice.setOnClickListener {

            task.difficulty = Difficulty.easy
            context.changeToDifficulty(Difficulty.easy)

        }
        normal_choice.setOnClickListener {

            task.difficulty = Difficulty.normal
            context.changeToDifficulty(Difficulty.normal)

        }
        hard_choice.setOnClickListener {

            task.difficulty = Difficulty.hard
            context.changeToDifficulty(Difficulty.hard)

        }

        context.changeToDifficulty(task.difficulty)
    }

    companion object {
        public val IS_WAS_CREATING_TASK = "IS_WAS_CREATING_TASK"
    }
}