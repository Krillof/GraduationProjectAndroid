package com.example.graduationprojectandroid.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.fragments.for_main_page.adapters.Habit
import com.example.graduationprojectandroid.fragments.for_main_page.adapters.Task
import com.example.graduationprojectandroid.fragments.for_main_page.dos.Dos
import com.example.graduationprojectandroid.fragments.for_main_page.inventory.Inventory
import com.example.graduationprojectandroid.network.DataService

class MainPage : AppCompatActivity() {

    enum class MainPageStates(val number: Int){
        DOS(0),
        INVENTORY(1)
    }

    companion object {
        val ARG_HABIT = "habit"
        val ARG_TASK = "task"

        var currentState: MainPageStates = MainPageStates.DOS
    }

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

    fun updatePage(){
        val items = listOf<View>(
            findViewById(R.id.drawer_item1_rectangle),
            findViewById(R.id.drawer_item2_rectangle),
            findViewById(R.id.drawer_item3_rectangle),
            findViewById(R.id.drawer_item4_rectangle)
        )

        when(currentState) {
            MainPageStates.DOS -> {
                initDos()
            }
            MainPageStates.INVENTORY -> {
                initInventory()
            }
        }

        items.forEach {
            it.setBackgroundResource(R.drawable.drawer_menu_item_off)
        }


        items[currentState.number].setBackgroundResource(R.drawable.drawer_menu_item_on)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        val open_menu_area = findViewById<View>(R.id.top_menu_click_area)
        open_menu_area.setOnClickListener {
            open_menu()
        }

        val items = listOf<View>(
            findViewById(R.id.drawer_item1_rectangle),
            findViewById(R.id.drawer_item2_rectangle),
            findViewById(R.id.drawer_item3_rectangle),
            findViewById(R.id.drawer_item4_rectangle)
        )

        items[MainPageStates.DOS.number].setOnClickListener {
            currentState = MainPageStates.DOS
            updatePage()
        }

        items[MainPageStates.INVENTORY.number].setOnClickListener {
            currentState = MainPageStates.INVENTORY
            updatePage()
        }

        updatePage()
    }

    private fun initInventory() {
        val inventory = Inventory.newInstance()

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_page_fragment, inventory)
            .commit()
    }

    private fun initDos(){
        val is_was_creating_tasks
                = intent.extras?.get(CreatingTask.IS_WAS_CREATING_TASK) as Boolean?

        val dos: Dos =  Dos.newInstance(
            if (is_was_creating_tasks == true) true else false, // because can be null - not redundant
            { open_creating_habit(it) },
            { open_creating_task(it) }
        )

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_page_fragment, dos)
            .commit()

    }



}