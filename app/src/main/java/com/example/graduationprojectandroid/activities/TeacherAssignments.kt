package com.example.graduationprojectandroid.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.fragments.Header
import com.example.graduationprojectandroid.data.Items.TeacherItem
import com.example.graduationprojectandroid.fragments.for_main_page.dos.HabitsList
import com.example.graduationprojectandroid.fragments.for_main_page.dos.TasksList
import com.example.graduationprojectandroid.network.DataService

class TeacherAssignments : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_assignments)

        val teacher: TeacherItem = (intent.extras?.get(ARG_TEACHER) as TeacherItem?)!!

        val showTeacherItem = findViewById<View>(R.id.show_teacher_item)
        val loginHeader = showTeacherItem.findViewById<TextView>(R.id.header)
        val picture = showTeacherItem.findViewById<ImageView>(R.id.picture)
        DataService.setOtherUserFacePicture(teacher.login, picture)
        loginHeader.text = teacher.login

        val colorRectangle = findViewById<View>(R.id.color_rectangle)

        val header: Header = Header.newInstance(getString(R.string.your_assignments_from))
        val choiceHabitsBackground = findViewById<View>(R.id.choice_1)
        val choiceTasksBackground = findViewById<View>(R.id.choice_2)
        val choiceHabitsClickArea = findViewById<View>(R.id.choice_click_area_1)
        val choiceTasksClickArea = findViewById<View>(R.id.choice_click_area_2)

        choiceHabitsBackground.visibility = View.INVISIBLE
        choiceTasksBackground.visibility = View.INVISIBLE

        supportFragmentManager.beginTransaction()
            .replace(R.id.page_header, header)
            .commit()

        choiceHabitsClickArea.setOnClickListener {
            choiceHabitsBackground.visibility = View.VISIBLE
            choiceTasksBackground.visibility = View.INVISIBLE

            colorRectangle.background = resources.getDrawable(R.drawable.blue_rectangle)

            DataService.getUserData { user ->
                val habitsList: HabitsList = HabitsList.newInstance(
                    teacher.login, user.login, true
                ){
                    // nothing, because habit is not yours
                }

                supportFragmentManager.beginTransaction()
                    .replace(R.id.assignments_list, habitsList)
                    .commit()
            }
        }

        choiceTasksClickArea.setOnClickListener {
            choiceHabitsBackground.visibility = View.INVISIBLE
            choiceTasksBackground.visibility = View.VISIBLE

            colorRectangle.background = resources.getDrawable(R.drawable.light_gray_rectangle)

            DataService.getUserData { user ->
                val tasksList: TasksList = TasksList.newInstance(
                    teacher.login, user.login, true
                ){
                    // nothing, because task is not yours
                }

                supportFragmentManager.beginTransaction()
                    .replace(R.id.assignments_list, tasksList)
                    .commit()

            }
        }

        choiceHabitsClickArea.performClick()

    }

    companion object {
        const val ARG_TEACHER = "teacher"
    }
}