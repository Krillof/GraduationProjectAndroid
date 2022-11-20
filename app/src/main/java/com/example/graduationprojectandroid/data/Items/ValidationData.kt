package com.example.graduationprojectandroid.data.Items

data class ValidationData(
    var isValid: String? = null,
    var isLoginInvalid: String? = null,
    var message: String? = null,
    var token: String? = null
){
    fun isValid(): Boolean {
        return isValid == "1"
    }

    fun isLoginInvalid(): Boolean {
        return isLoginInvalid == "1"
    }
}
