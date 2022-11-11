package com.example.graduationprojectandroid.data.Items

import android.view.View
import com.example.graduationprojectandroid.data.States.Difficulty
import com.example.graduationprojectandroid.data.States.HabitDoneStates
import java.io.Serializable

class Habit(
    val id: Int,
    val loginFrom: String,
    val loginTo: String,
    var header: String,
    var text: String,
    var done: HabitDoneStates
    ) : Serializable{

    public var difficulty: Difficulty = Difficulty.normal
    public var isHabitGood: Boolean = true
}
