package com.example.graduationprojectandroid.data.Items

import com.example.graduationprojectandroid.fragments.for_main_page.adapters.TasksAdapter
import java.io.Serializable


// Knows its parent
class ParentizedSubtask(done: Boolean, text: String) : Serializable, Subtask(done, text) {

    private var parent_adapter: TasksAdapter? = null

    constructor() : this(false, ""){}

    public fun setParent(parent: TasksAdapter){
        parent_adapter = parent
    }

    public fun getParent(): TasksAdapter
            = parent_adapter!!
}