package com.example.graduationprojectandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val context = this
        supportFragmentManager.commit {
            val get_login_password = GetLoginPassword() {
                supportFragmentManager.commit {
                    val get_code = GetCode() {
                        startActivity(Intent(context, MainPage::class.java))
                        finish()
                    }
                    replace(R.id.main_login_fragment, get_code)
                }
            }
            replace(R.id.main_login_fragment, get_login_password)

            val or_enter_by: OrEnterBy = OrEnterBy.newInstance() {
                TODO("Another way to get in")
            }
            add(R.id.or_enter_by, or_enter_by)
        }
    }

}