package com.example.graduationprojectandroid.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.fragments.for_main_page.dos.Dos

class MainPage : AppCompatActivity() {




    //-------------------------------------------
    //TMP
    private val login: String = "Константин Константиновский"
    //TMP
    //-------------------------------------------

    fun open_creating_habit(){
        startActivity(Intent(this, CreatingHabit::class.java))
        finish()
    }

    fun open_menu(){
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.openDrawer(GravityCompat.START)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main_page)

        val dos = Dos.newInstance(login, { open_menu() }, { open_creating_habit() })

        supportFragmentManager.beginTransaction()
                .replace(R.id.main_page_fragment, dos)
                .commit()

    }



}