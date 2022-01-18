package com.example.graduationprojectandroid.fragments.for_main_page.adapters

import android.view.View
import java.io.Serializable

class Habit(
    val id: Int,
    var header: String,
    var text: String,
    var done: Int,// use with HabitDoneStates
    val visibility: Int = View.VISIBLE
    ) : Serializable{

    public var difficulty: Difficulty = Difficulty.normal
    public var isHabitGood: Boolean = true
}
