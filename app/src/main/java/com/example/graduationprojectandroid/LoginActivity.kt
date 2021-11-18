package com.example.graduationprojectandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Group
import androidx.core.view.isVisible
import androidx.fragment.app.commit
import androidx.fragment.app.replace

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportFragmentManager.commit {
            val get_login_password = GetLoginPassword() {
                supportFragmentManager.commit {
                    val get_code = GetCode() {
                        TODO("After code code")
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