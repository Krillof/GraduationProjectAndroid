package com.example.graduationprojectandroid

import android.content.Context

object PreferencesService {

    public val SAVING_TOKEN_STR = "DO_TOKEN"
    private val KEY_TO_TOKEN_STR = "TOKEN"

    private val SAVING_LOGIN_STR = "DO_LOGIN"
    private val KEY_TO_LOGIN_STR = "LOGIN"

    fun isTokenExists() : Boolean{
        val sharedPreferences = App.getAppContext().getSharedPreferences(SAVING_TOKEN_STR, Context.MODE_PRIVATE)
        return sharedPreferences.contains(KEY_TO_TOKEN_STR)
    }

    fun loadToken() : String? {
        val sharedPreferences = App.getAppContext().getSharedPreferences(SAVING_TOKEN_STR, Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_TO_TOKEN_STR, "")
    }

    fun saveToken(token: String){
        val sharedPreferences =  App.getAppContext().getSharedPreferences(SAVING_TOKEN_STR, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(KEY_TO_TOKEN_STR, token).apply()
    }



    fun loadLogin() : String? {
        val sharedPreferences = App.getAppContext().getSharedPreferences(SAVING_LOGIN_STR, Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_TO_LOGIN_STR, "")
    }

    fun saveLogin(login: String){
        val sharedPreferences =  App.getAppContext().getSharedPreferences(SAVING_LOGIN_STR, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(KEY_TO_LOGIN_STR, login).apply()
    }
}