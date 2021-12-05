package com.example.graduationprojectandroid.fragments.for_main_page.adapters

import android.view.View

class Task(
    public val id: Int,
    public val header: String,
    public val text: String,
    private val done: MutableList<Boolean>,
    public val subtasks: MutableList<String>,
    public val visibility: Int = View.VISIBLE
    )
{
    public fun setDone(value: Boolean){
        for (i in 0..done.size){
            done[i] = value
        }
    }

    public fun setSubtaskDone(index: Int, value: Boolean){
        done[index] = value
    }

    public fun isDone(): Boolean{
        var check_done = true
        for (i in 0..done.size){
            check_done = check_done && done[i]
        }
        return check_done
    }

    public fun isSubtaskDone(index: Int): Boolean{
        return done[index]
    }

}