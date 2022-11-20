package com.example.graduationprojectandroid.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.example.graduationprojectandroid.fragments.for_login.GetLoginPassword
import com.example.graduationprojectandroid.fragments.for_login.OrEnterBy
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.data.Items.ValidationData
import com.example.graduationprojectandroid.network.DataService
import kotlin.system.exitProcess


class LoginActivity : AppCompatActivity() {

    companion object {
        val LOGIN = "LOGIN"
        val PASSWORD = "PASSWORD"
    }

    private fun setGetLoginPassword(validationData: ValidationData, prevLogin: String = ""){
        supportFragmentManager.commit {
            var loginErrorMessage: String = ""
            var passwordErrorMessage: String = ""
            if (validationData.isLoginInvalid()){
                loginErrorMessage = validationData.message!!
            } else {
                passwordErrorMessage = validationData.message!!
            }

            val getLoginPassword = GetLoginPassword(
                { login, password, isRegistering ->
                    tryGetIn(login, password, isRegistering)
                },
                loginErrorMessage, passwordErrorMessage, prevLogin)
            replace(R.id.main_login_fragment, getLoginPassword)
        }
    }


    private fun tryGetIn(login: String, password: String, isRegistering: Boolean){
        val context = this

        if (isRegistering) {
            DataService.tryRegister(login, password) { validationData ->
                if (validationData.isValid()) {
                    DataService.saveToken(validationData.token!!)
                    DataService.saveLogin(login)
                    MainPage.currentState = MainPage.MainPageStates.DOS
                    startActivity(Intent(context, ChangingAvatar::class.java))
                    finish()
                } else setGetLoginPassword(validationData, login)
                /*
                TODO: why is it here?
                else {
                    var infoDialogue: InfoDialogue? = null
                    infoDialogue = InfoDialogue.newInstance(it){
                        //it's ok that "it" isn't in use - program just informing user
                        infoDialogue?.dismissAllowingStateLoss()
                    }
                    infoDialogue.show(supportFragmentManager, "info_dialogue")
                }*/
            }
        } else {
            DataService.tryEnter(login, password){ validationData ->
                if (validationData.isValid()){
                    DataService.saveToken(validationData.token!!)
                    DataService.saveLogin(login)
                    startActivity(Intent(this, MainPage::class.java))
                    finish()
                } else setGetLoginPassword(validationData, login)
            }
        }
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
        android.os.Process.killProcess(android.os.Process.myPid())
        exitProcess(0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportFragmentManager.commit {
            val getLoginPassword = GetLoginPassword(
                { login, password, isRegistering ->

                    tryGetIn(login, password, isRegistering)
                },
                "", "")
            replace(R.id.main_login_fragment, getLoginPassword)

            val orEnterBy: OrEnterBy = OrEnterBy.newInstance() {
                TODO("Another way to get in")
            }
            add(R.id.or_enter_by, orEnterBy)
        }
    }

}