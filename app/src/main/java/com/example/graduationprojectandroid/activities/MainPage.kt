package com.example.graduationprojectandroid.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.fragments.for_main_page.adapters.Habit
import com.example.graduationprojectandroid.fragments.for_main_page.adapters.Task
import com.example.graduationprojectandroid.fragments.for_main_page.dos.Dos

class MainPage : AppCompatActivity() {


    companion object {
        public val ARG_HABIT = "habit"
        public val ARG_TASK = "task"
    }
    //-------------------------------------------
    //TMP
    private val login: String = "Константин Константиновский"
    //TMP
    //-------------------------------------------

    fun open_creating_habit(habit: Habit?){
        val intent = Intent(this, CreatingHabit::class.java)
        intent.putExtra(ARG_HABIT, habit)
        startActivity(intent)
        finish()
    }

    fun open_creating_task(task: Task?){
        val intent = Intent(this, CreatingTask::class.java)
        intent.putExtra(ARG_TASK, task)
        startActivity(intent)
        finish()
    }

    fun open_menu(){
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.openDrawer(GravityCompat.START)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main_page)

        val is_was_creating_tasks
            = intent.extras?.get(CreatingTask.IS_WAS_CREATING_TASK) as Boolean?

        val dos = Dos.newInstance(
            if (is_was_creating_tasks == true) true else false, // because can be null - not redundant
            login,
            { open_menu() },
            { open_creating_habit(it) },
            { open_creating_task(it) })

        supportFragmentManager.beginTransaction()
                .replace(R.id.main_page_fragment, dos)
                .commit()

    }



}