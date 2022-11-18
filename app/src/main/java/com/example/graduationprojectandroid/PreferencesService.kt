package com.example.graduationprojectandroid

import android.content.Context

object PreferencesService {

    private val SAVING_TOKEN_STR = "DO_TOKEN"
    private val KEY_TO_TOKEN_STR = "TOKEN"

    fun isTokenExists() : Boolean{
        val sharedPreferences = App.getAppContext().getSharedPreferences(SAVING_TOKEN_STR, Context.MODE_PRIVATE)
        return sharedPreferences.contains(KEY_TO_TOKEN_STR)
    }

    fun loadToken() : String? {
        val sharedPreferences = App.getAppContext().getSharedPreferences(SAVING_TOKEN_STR, Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_TO_TOKEN_STR, "")
    }

    fun saveToken(login: String){
        val sharedPreferences =  App.getAppContext().getSharedPreferences(SAVING_TOKEN_STR, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(KEY_TO_TOKEN_STR, login).apply()
    }


}