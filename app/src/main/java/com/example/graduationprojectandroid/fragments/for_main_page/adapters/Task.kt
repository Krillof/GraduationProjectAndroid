package com.example.graduationprojectandroid.fragments.for_main_page.adapters

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import java.io.Serializable

class Task(
    public val id: Int,
    public var header: String,
    public var text: String,
    public val subtasks: MutableList<Subtask>,
    public val visibility: Int = View.VISIBLE,
    public var show_subtasks_always: Int = View.VISIBLE
    ) : Serializable
{
    private var show_subtasks_current: Int = View.GONE
    private var adapter = SubtaskAdapter(subtasks)
    private var done: Boolean = false

    public var isEveryday: Boolean = false
    public var isEveryweek: Boolean = false
    public var isEverymonth: Boolean = false
    public var difficulty: Difficulty = Difficulty.normal

    public fun setParentAdapterForSubtasks(value: TasksAdapter) {
        for (i in 0 until subtasks.size){
            subtasks[i].setParent(value)
        }
    }

    public fun getAdapter(): SubtaskAdapter{
        return adapter
    }

    public fun getCurrentShowSubtasks(): Int = show_subtasks_current

    public fun setCurrentShowSubtasks(value: Int){
        show_subtasks_current = value
    }

    public fun setDone(value: Boolean){
        if (subtasks.size != 0)
            for (i in 0 until subtasks.size){
                subtasks[i].done = value
            }
        else
            done = value
    }

    public fun setSubtaskDone(index: Int, value: Boolean){
        subtasks[index].done = value
    }

    public fun isDone(): Boolean{
        if (subtasks.size != 0) {
            var check_done = true
            for (i in 0 until subtasks.size) {
                check_done = check_done && subtasks[i].done
            }
            return check_done
        } else {
            return done
        }
    }

    public fun isSubtaskDone(index: Int): Boolean{
        return subtasks[index].done
    }



}