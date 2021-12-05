package com.example.graduationprojectandroid.fragments.for_main_page.adapters

import android.view.View

data class Habit(
    val id: Int,
    var header: String,
    var text: String,
    var done: Int,// use with HabitDoneStates
    val visibility: Int = View.VISIBLE
    )
