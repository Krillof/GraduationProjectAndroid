package com.example.graduationprojectandroid.network.endpoints

data class SimpleServerAnswer(
    override var errors: String? = null,
    var data: String? = null
) : ServerAnswer()