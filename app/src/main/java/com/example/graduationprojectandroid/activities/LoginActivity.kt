package com.example.graduationprojectandroid.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.example.graduationprojectandroid.fragments.for_login.GetLoginPassword
import com.example.graduationprojectandroid.fragments.for_login.OrEnterBy
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.fragments.for_main_page.InfoDialogue
import com.example.graduationprojectandroid.network.DataService


class LoginActivity : AppCompatActivity() {

    companion object {
        val LOGIN = "LOGIN"
        val PASSWORD = "PASSWORD"
    }

    private fun nextActivity(login: String, password: String, isRegistering: Boolean){
        if (isRegistering) {
            val context = this
            DataService.registerUser(login, password) {
                if (it == "") {
                    MainPage.currentState = MainPage.MainPageStates.DOS
                    startActivity(Intent(context, ChangingAvatar::class.java))
                    finish()
                } else {
                    var infoDialogue: InfoDialogue? = null
                    infoDialogue = InfoDialogue.newInstance(it){
                        //it's ok that "it" isn't in use - program just informing user
                        infoDialogue?.dismissAllowingStateLoss()
                    }
                    infoDialogue.show(supportFragmentManager, "info_dialogue")
                }
            }
        } else {
            startActivity(Intent(this, MainPage::class.java))
            finish()
        }
    }

    private fun setGetLoginPassword(loginErrorMessage: String, passwordErrorMessage: String){
        supportFragmentManager.commit {
            val get_login_password = GetLoginPassword(
                { login, password, isRegistering ->
                    tryGetIn(login, password, isRegistering)
                },
                "", "")
            replace(R.id.main_login_fragment, get_login_password)
        }
    }


    private fun tryGetIn(login: String, password: String, isRegistering: Boolean){
        if (isRegistering){
            val newLoginAnswer = DataService.checkNewLogin(login){
                if (it == ""){
                    val passwordAnswer = DataService.checkPassword(password){
                        if (it == ""){
                            // go to avatar creating
                            nextActivity(login, password, true)
                        } else {
                            // write error message for password from it
                            setGetLoginPassword("", it)
                        }
                    }
                } else {
                    // write error message for login from it
                    setGetLoginPassword(it, "")
                }
            }

        } else {
            val tryLogin = DataService.tryLogin(login){
                if (it == ""){
                    val tryPassword = DataService.tryEnter(login, password){
                        if (it == ""){
                            // go to main page
                            nextActivity(login, password, false)
                        } else {
                            // write error message for password from it
                            setGetLoginPassword("", it)
                        }
                    }
                } else {
                    // write error message for login from it
                    setGetLoginPassword(it, "")
                }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportFragmentManager.commit {
            val get_login_password = GetLoginPassword(
                { login, password, isRegistering ->

                    tryGetIn(login, password, isRegistering)
                },
                "", "")
            replace(R.id.main_login_fragment, get_login_password)

            val or_enter_by: OrEnterBy = OrEnterBy.newInstance() {
                TODO("Another way to get in")
            }
            add(R.id.or_enter_by, or_enter_by)
        }
    }

}