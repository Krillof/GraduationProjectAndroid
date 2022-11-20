package com.example.graduationprojectandroid.network.endpoints

import com.example.graduationprojectandroid.network.DataService

data class ServerAnswer(
    var Errors: String?,
    var Data: String?
) {
    fun checkErrors(){
        if (Errors == null)
            DataService.openErrorPage("Errors is null")

        if (Errors != "")
            DataService.openErrorPage(Data!!)
    }
}
