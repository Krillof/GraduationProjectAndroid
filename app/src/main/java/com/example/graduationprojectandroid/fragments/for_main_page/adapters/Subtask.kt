package com.example.graduationprojectandroid.fragments.for_main_page.adapters

import java.io.Serializable

open class Subtask(
    var done: Boolean,
    var text: String
) : Serializable {

    constructor() : this(false, ""){}
}
