package com.example.graduationprojectandroid.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.DialogFragment
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.fragments.AskQuestionDialogue
import com.example.graduationprojectandroid.fragments.for_main_page.News
import com.example.graduationprojectandroid.fragments.for_main_page.adapters.Habit
import com.example.graduationprojectandroid.fragments.for_main_page.adapters.Task
import com.example.graduationprojectandroid.fragments.for_main_page.dos.Dos
import com.example.graduationprojectandroid.fragments.for_main_page.inventory.Inventory
import com.example.graduationprojectandroid.fragments.for_main_page.other_users.ShowTeachers
import kotlin.system.exitProcess

class MainPage : AppCompatActivity() {

    enum class MainPageStates(val number: Int){
        DOS(0),
        INVENTORY(1),
        AVATAR(2),
        SHOW_TEACHERS(3),
        FIND_TEACHERS(4),
        SHOW_STUDENTS(5),
        NEWS(6)
    }

    companion object {
        const val ARG_HABIT = "habit"
        const val ARG_TASK = "task"
        const val ARG_NEWS_ITEM_ID = "news_item"

        var currentState: MainPageStates = MainPageStates.DOS
        lateinit var items: List<View>
        lateinit var inits: List<()->Unit>
    }

    private fun openCreatingHabit(habit: Habit?){
        val intent = Intent(this, CreatingHabit::class.java)
        intent.putExtra(ARG_HABIT, habit)
        startActivity(intent)
        finish()
    }

    private fun openCreatingTask(task: Task?){
        val intent = Intent(this, CreatingTask::class.java)
        intent.putExtra(ARG_TASK, task)
        startActivity(intent)
        finish()
    }

    private fun openReadingNewsItem(newsItemId: Int){
        val intent = Intent(this, NewsItemPage::class.java)
        intent.putExtra(ARG_NEWS_ITEM_ID, newsItemId)
        startActivity(intent)
        finish()
    }

    private fun openMenu(){
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.openDrawer(GravityCompat.START)
    }

    private fun closeMenu(){
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
    }

    private fun updatePage(){
        inits[currentState.number]()

        items.forEach {
            it.setBackgroundResource(R.drawable.drawer_menu_item_off)
        }
        items[currentState.number].setBackgroundResource(R.drawable.drawer_menu_item_on)
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
        android.os.Process.killProcess(android.os.Process.myPid())
        exitProcess(1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        val isGotFromReadingNewsItem
                = intent.extras?.get(NewsItemPage.IS_GOT_FROM_READING_NEWS_ITEM) as Boolean?
        if (isGotFromReadingNewsItem != null){
            currentState = MainPageStates.NEWS
        }

        val openMenuArea = findViewById<View>(R.id.top_menu_click_area)
        openMenuArea.setOnClickListener {
            openMenu()
        }

        items = listOf<View>(
            findViewById(R.id.drawer_item1_rectangle),
            findViewById(R.id.drawer_item2_rectangle),
            findViewById(R.id.drawer_item3_rectangle),
            findViewById(R.id.drawer_item4_rectangle),
            findViewById(R.id.drawer_item5_rectangle),
            findViewById(R.id.drawer_item6_rectangle),
            findViewById(R.id.drawer_item7_rectangle),
            )

        inits = listOf(
            {initDos()}, {initInventory()}, {initAvatar()}, {initShowTeachers()},
            {initFindTeacher()}, {initShowStudents()}, {initNews()}
        )

        for (i in items.indices){
            items[i].setOnClickListener {
                currentState = MainPageStates.values()[i]
                updatePage()
            }
        }

        items[MainPageStates.AVATAR.number].setOnClickListener {
            var df: DialogFragment? = null
            df = AskQuestionDialogue(getString(R.string.is_change_avatar)) {
                df?.dismissAllowingStateLoss()
                if (it == true){
                    currentState = MainPageStates.AVATAR
                    updatePage()
                }
            }
            df.show(supportFragmentManager, "save_changes")
        }

        updatePage()
    }

    private fun initInventory() {
        closeMenu()
        val inventory = Inventory.newInstance()

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_page_fragment, inventory)
            .commit()
    }

    private fun initDos(){
        closeMenu()
        val isWasCreatingTasks
                = intent.extras?.get(CreatingTask.IS_WAS_CREATING_TASK) as Boolean?

        val dos: Dos =  Dos.newInstance(
            isWasCreatingTasks == true, // because can be null - not redundant
            { openCreatingHabit(it) },
            { openCreatingTask(it) }
        )

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_page_fragment, dos)
            .commit()
    }

    private fun initAvatar() {
        closeMenu()
        startActivity(Intent(this, ChangingAvatar::class.java))
        finish()
    }

    private fun initShowTeachers(){
        closeMenu()
        val showTeachersFragment: ShowTeachers = ShowTeachers.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_page_fragment, showTeachersFragment)
            .commit()
    }

    private fun initFindTeacher(){
        closeMenu()

    }

    private fun initShowStudents(){
        closeMenu()

    }

    private fun initNews() {
        closeMenu()
        val news = News.newInstance(){
            openReadingNewsItem(it)
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_page_fragment, news)
            .commit()
    }

}