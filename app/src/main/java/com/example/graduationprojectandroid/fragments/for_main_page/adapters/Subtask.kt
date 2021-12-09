package com.example.graduationprojectandroid.fragments.for_main_page.adapters

class Subtask(
    var done: Boolean,
    var text: String
){
    private var parent_adapter: TasksAdapter? = null

    public fun setParent(parent: TasksAdapter){
        parent_adapter = parent
    }

    public fun getParent():TasksAdapter
        = parent_adapter!!
}
