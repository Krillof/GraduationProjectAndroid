package com.example.graduationprojectandroid.fragments.for_main_page.adapters

import java.io.Serializable

class ParentizedSubtask(done: Boolean, text: String) : Serializable, Subtask(done, text) {

    private var parent_adapter: TasksAdapter? = null

    constructor() : this(false, ""){}

    public fun setParent(parent: TasksAdapter){
        parent_adapter = parent
    }

    public fun getParent():TasksAdapter
            = parent_adapter!!
}