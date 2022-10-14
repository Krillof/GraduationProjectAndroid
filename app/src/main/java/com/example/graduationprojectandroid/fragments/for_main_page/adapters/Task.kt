package com.example.graduationprojectandroid.fragments.for_main_page.adapters

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import java.io.Serializable

open class Task(
    val id: Int,
    val loginFrom: String,
    val loginTo: String,
    var header: String,
    var text: String,
    private val subtasks: MutableList<Subtask>,
    var isEveryday: Boolean = false,
    var isEveryweek: Boolean = false,
    var isEverymonth: Boolean = false,
    var difficulty: Difficulty = Difficulty.normal

) : Serializable {

    protected var done: Boolean = false

    fun setParentizedSubtasksAsSubtasks(st: MutableList<ParentizedSubtask>){
        st.forEach {
            subtasks.add(ParentizedSubtask(it.done, it.text))
        }
    }

    fun getSubtasks() : MutableList<Subtask>{
        return subtasks
    }

    fun getSubtasksAsParentizedSubtasks() : MutableList<ParentizedSubtask>{
        var parentizedSubtasks: MutableList<ParentizedSubtask>
                = MutableList(0) { ParentizedSubtask() }
        subtasks.forEach {
            parentizedSubtasks.add(ParentizedSubtask(it.done, it.text))
        }
        return parentizedSubtasks
    }

    open fun setFullDone(value: Boolean){
        if (subtasks.size != 0)
            for (el in subtasks){
                el.done = value
            }
        else
            done = value
    }

    open fun setSubtaskDone(index: Int, value: Boolean){
        subtasks[index].done = value
    }

    open fun isDone(): Boolean{
        if (subtasks.size != 0) {
            var check_done = true
            for (el in subtasks) {
                check_done = check_done && el.done
            }
            return check_done
        } else {
            return done
        }
    }

    open fun isSubtaskDone(index: Int): Boolean{
        return subtasks[index].done
    }

    open fun howManySubtasksDone(): Int{
        var amount: Int = 0
        for (el in subtasks) {
            if (el.done)
                amount++
        }
        return amount
    }

}