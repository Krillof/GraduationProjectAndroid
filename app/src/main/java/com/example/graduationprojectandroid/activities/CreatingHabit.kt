package com.example.graduationprojectandroid.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.fragments.Button
import com.example.graduationprojectandroid.fragments.Header
import com.example.graduationprojectandroid.fragments.SuperDesignInput

class CreatingHabit : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creating_habit)
        val context = this
        supportFragmentManager.commit {

            val header: Header = Header.newInstance(
                getString(R.string.creating_habit)
            )
            add(R.id.header, header)

            val input1: SuperDesignInput = SuperDesignInput.newInstance(
                getString(R.string.habit_name)
            )
            add(R.id.super_design_input, input1)

            val button: Button = Button.newInstance(getString(R.string.save)){

            }
            add(R.id.button_confirm, button)
        }
    }
}