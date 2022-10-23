package com.example.graduationprojectandroid.data.Items

import java.io.Serializable

open class Subtask(
    var done: Boolean,
    var text: String
) : Serializable {

    constructor() : this(false, ""){}
}
