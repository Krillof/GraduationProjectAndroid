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
import com.example.graduationprojectandroid.data.Items.Habit
import com.example.graduationprojectandroid.data.Items.StudentItem
import com.example.graduationprojectandroid.data.Items.Task
import com.example.graduationprojectandroid.data.Items.TeacherItem
import com.example.graduationprojectandroid.fragments.for_main_page.dos.Dos
import com.example.graduationprojectandroid.fragments.for_main_page.inventory.Inventory
import com.example.graduationprojectandroid.fragments.for_main_page.other_users.FindTeachers
import com.example.graduationprojectandroid.fragments.for_main_page.other_users.ShowStudents
import com.example.graduationprojectandroid.fragments.for_main_page.other_users.ShowTeachers
import com.example.graduationprojectandroid.fragments.for_main_page.other_users.StudyRequests
import com.example.graduationprojectandroid.network.DataService
import kotlin.system.exitProcess

class MainPage : AppCompatActivity() {

    enum class MainPageStates(val number: Int){
        DOS(0),
        INVENTORY(1),
        AVATAR(2),
        SHOW_TEACHERS(3),
        FIND_TEACHERS(4),
        SHOW_STUDENTS(5),
        STUDY_REQUESTS(6),
        NEWS(7)
    }

    companion object {
        var currentState: MainPageStates = MainPageStates.DOS
        lateinit var items: List<View>
        lateinit var inits: List<()->Unit>
    }

    private fun openCreatingHabit(habit: Habit?){
        DataService.getUserData { user ->
            val intent = Intent(this, CreatingHabit::class.java)
            intent.putExtra(CreatingHabit.ARG_HABIT, habit)
            intent.putExtra(CreatingHabit.ARG_LOGIN_FROM, "") // all habits from all
            intent.putExtra(CreatingHabit.ARG_LOGIN_TO, user.login)
            startActivity(intent)
        }
    }

    private fun openCreatingTask(task: Task?){
        DataService.getUserData { user ->
            val intent = Intent(this, CreatingTask::class.java)
            intent.putExtra(CreatingTask.ARG_TASK, task)
            intent.putExtra(CreatingTask.ARG_LOGIN_FROM, "") // all tasks from all
            intent.putExtra(CreatingTask.ARG_LOGIN_TO, user.login)
            startActivity(intent)
        }
    }

    private fun openReadingNewsItem(newsItemId: Int){
        val intent = Intent(this, NewsItemPage::class.java)
        intent.putExtra(NewsItemPage.ARG_NEWS_ITEM_ID, newsItemId)
        startActivity(intent)
    }

    private fun openTeacherAssignments(teacher: TeacherItem){
        val intent = Intent(this, TeacherAssignments::class.java)
        intent.putExtra(TeacherAssignments.ARG_TEACHER, teacher)
        startActivity(intent)
    }

    private fun openStudentAssignments(student: StudentItem){
        val intent = Intent(this, StudentAssignments::class.java)
        intent.putExtra(StudentAssignments.ARG_STUDENT, student)
        startActivity(intent)
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
            findViewById(R.id.drawer_item8_rectangle)
            )

        inits = listOf(
            {initDos()}, {initInventory()}, {initAvatar()}, {initShowTeachers()},
            {initFindTeacher()}, {initShowStudents()}, {initStudyRequests()}, {initNews()}
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

        val dos: Dos = Dos.newInstance(
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
        val showTeachersFragment: ShowTeachers = ShowTeachers.newInstance {
            openTeacherAssignments(
                it
            )
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_page_fragment, showTeachersFragment)
            .commit()
    }

    private fun initFindTeacher(){
        closeMenu()
        val findTeachersFragment: FindTeachers = FindTeachers.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_page_fragment, findTeachersFragment)
            .commit()
    }

    private fun initShowStudents(){
        closeMenu()
        val showStudentsFragment: ShowStudents = ShowStudents.newInstance {
            openStudentAssignments(
                it
            )
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_page_fragment, showStudentsFragment)
            .commit()
    }

    private fun initStudyRequests(){
        closeMenu()
        val studyRequestsFragment: StudyRequests = StudyRequests.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_page_fragment, studyRequestsFragment)
            .commit()
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