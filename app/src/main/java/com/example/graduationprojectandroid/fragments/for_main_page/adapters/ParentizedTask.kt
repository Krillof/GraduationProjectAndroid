package com.example.graduationprojectandroid.fragments.for_main_page.adapters

import android.view.View
import java.io.Serializable

class ParentizedTask(
    id: Int,
    loginFrom: String,
    loginTo: String,
    header: String,
    text: String,
    subtasks: MutableList<Subtask>,
    public var visibility: Int = View.VISIBLE,
    public var show_subtasks_always: Int = View.VISIBLE,
    isEveryday: Boolean = false,
    isEveryweek: Boolean = false,
    isEverymonth: Boolean = false,
    difficulty: Difficulty = Difficulty.normal
) : Serializable, Task(
    id, loginFrom, loginTo, header, text, subtasks, isEveryday, isEveryweek, isEverymonth, difficulty
)
{
    private var parentizedSubtasksList: MutableList<ParentizedSubtask> = MutableList(0) { ParentizedSubtask() }

    private var show_subtasks_current: Int = View.GONE

    init {
        subtasks.forEach {
            parentizedSubtasksList.add(ParentizedSubtask(it.done, it.text))
        }
    }

    private var adapter = SubtaskAdapter(parentizedSubtasksList)

    fun setParentAdapterForSubtasks(value: TasksAdapter) {
        for (el in parentizedSubtasksList) {
            el.setParent(value)
        }
    }

    fun getParentizedSubtasks() : MutableList<ParentizedSubtask>{
        return parentizedSubtasksList
    }

    fun getAdapter(): SubtaskAdapter{
        return adapter
    }

    fun getCurrentShowSubtasks(): Int = show_subtasks_current

    fun setCurrentShowSubtasks(value: Int){
        show_subtasks_current = value
    }

    override fun setFullDone(value: Boolean){
        if (parentizedSubtasksList.size != 0)
            for (el in parentizedSubtasksList) {
                el.done = value
            }
        else
            done = value
    }

    override fun setSubtaskDone(index: Int, value: Boolean){
        parentizedSubtasksList[index].done = value
    }

    override fun isDone(): Boolean{
        if (parentizedSubtasksList.size != 0) {
            var check_done = true
            for (el in parentizedSubtasksList) {
                check_done = check_done && el.done
            }
            return check_done
        } else {
            return done
        }
    }

    override fun isSubtaskDone(index: Int): Boolean{
        return parentizedSubtasksList[index].done
    }

    override fun howManySubtasksDone(): Int{
        var amount: Int = 0
        for (el in parentizedSubtasksList) {
            if (el.done)
                amount++
        }
        return amount
    }

}