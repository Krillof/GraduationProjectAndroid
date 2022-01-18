package com.example.graduationprojectandroid.fragments.for_main_page.adapters

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import java.io.Serializable

open class Task(
    public val id: Int,
    public var header: String,
    public var text: String,
    private val subtasks: MutableList<Subtask>,
    public var difficulty: Difficulty = Difficulty.normal
) : Serializable {

    protected var done: Boolean = false

    public fun setParentizedSubtasksAsSubtasks(st: MutableList<ParentizedSubtask>){
        st.forEach {
            subtasks.add(ParentizedSubtask(it.done, it.text))
        }
    }

    public open fun getSubtasks() : MutableList<Subtask>{
        return subtasks
    }

    public fun getSubtasksAsParentizedSubtasks() : MutableList<ParentizedSubtask>{
        var parentizedSubtasks: MutableList<ParentizedSubtask>
                = MutableList(0, { ParentizedSubtask() })
        subtasks.forEach {
            parentizedSubtasks.add(ParentizedSubtask(it.done, it.text))
        }
        return parentizedSubtasks
    }

    public open fun setFullDone(value: Boolean){
        if (subtasks.size != 0)
            for (el in subtasks){
                el.done = value
            }
        else
            done = value
    }

    public open fun setSubtaskDone(index: Int, value: Boolean){
        subtasks[index].done = value
    }

    public open fun isDone(): Boolean{
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

    public open fun isSubtaskDone(index: Int): Boolean{
        return subtasks[index].done
    }

    public open fun howManySubtasksDone(): Int{
        var amount: Int = 0
        for (el in subtasks) {
            if (el.done)
                amount++
        }
        return amount
    }

}