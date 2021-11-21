package com.example.graduationprojectandroid.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.fragments.Button

class CreatingHabit : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creating_habit)
        val context = this
        supportFragmentManager.commit {



            val button: Button = Button.newInstance(getString(R.string.save)){

            }
            add(R.id.button_confirm, button)
        }
    }
}