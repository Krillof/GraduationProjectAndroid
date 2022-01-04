package com.example.graduationprojectandroid.fragments.for_main_page.adapters

import java.io.Serializable

class Subtask(
    var done: Boolean,
    var text: String
) : Serializable{
    private var parent_adapter: TasksAdapter? = null

    public fun setParent(parent: TasksAdapter){
        parent_adapter = parent
    }

    public fun getParent():TasksAdapter
        = parent_adapter!!
}
