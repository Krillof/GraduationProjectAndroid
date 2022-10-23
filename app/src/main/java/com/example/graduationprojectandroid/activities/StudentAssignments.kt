package com.example.graduationprojectandroid.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.fragments.Header
import com.example.graduationprojectandroid.data.Items.StudentItem
import com.example.graduationprojectandroid.fragments.for_main_page.dos.HabitsList
import com.example.graduationprojectandroid.fragments.for_main_page.dos.TasksList
import com.example.graduationprojectandroid.network.DataService

class StudentAssignments : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_assignments)

        val student: StudentItem = (
                intent.extras?.get(ARG_STUDENT) as StudentItem?
                )!!

        val showTeacherItem = findViewById<View>(R.id.show_teacher_item)
        val loginHeader = showTeacherItem.findViewById<TextView>(R.id.header)
        val picture = showTeacherItem.findViewById<ImageView>(R.id.picture)
        DataService.setOtherUserFacePicture(student.login, picture)
        loginHeader.text = student.login

        val colorRectangle = findViewById<View>(R.id.color_rectangle)

        val header: Header = Header.newInstance(getString(R.string.your_assignments_for))
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
                    user.login, student.login
                ) { habit ->

                    val intent = Intent(this, CreatingHabit::class.java)
                    intent.putExtra(CreatingHabit.ARG_HABIT, habit)
                    intent.putExtra(CreatingHabit.ARG_LOGIN_FROM, user.login)
                    intent.putExtra(CreatingHabit.ARG_LOGIN_TO, student.login)
                    startActivity(intent)

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
                    user.login, student.login
                ) { task ->

                    val intent = Intent(this, CreatingTask::class.java)
                    intent.putExtra(CreatingTask.ARG_TASK, task)
                    intent.putExtra(CreatingTask.ARG_LOGIN_FROM, user.login)
                    intent.putExtra(CreatingTask.ARG_LOGIN_TO, student.login)
                    startActivity(intent)

                }

                supportFragmentManager.beginTransaction()
                    .replace(R.id.assignments_list, tasksList)
                    .commit()

            }
        }

        choiceHabitsClickArea.performClick()
    }

    companion object {
        const val ARG_STUDENT = "student"
    }
}