package com.example.graduationprojectandroid.network.endpoints

import com.example.graduationprojectandroid.network.DataService

abstract class ServerAnswer {
    abstract var errors: String?
    fun checkErrors() {
        if (errors != "") {
            DataService.openErrorPage()
        }
    }
}