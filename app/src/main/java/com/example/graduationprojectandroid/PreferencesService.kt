package com.example.graduationprojectandroid

import android.content.Context

object PreferencesService {

    private val SAVING_LOGIN_STR = "DO_LOGIN"
    private val KEY_TO_LOGIN_STR = "LOGIN"

    fun loadLogin(context: Context) : String? {
        val sharedPreferences =  context.getSharedPreferences(SAVING_LOGIN_STR, Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_TO_LOGIN_STR, "")
    }

    fun saveLogin(context: Context, login: String){
        val sharedPreferences =  context.getSharedPreferences(SAVING_LOGIN_STR, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(KEY_TO_LOGIN_STR, login).apply()
    }


}