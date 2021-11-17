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

        /*
        val include_login_entering_1 = findViewById<ConstraintLayout>(R.id.login_entering_1)
        val include_login_entering_2 = findViewById<ConstraintLayout>(R.id.login_entering_2)
        val include_or_enter_by = findViewById<RelativeLayout>(R.id.or_enter_by)


        val header_1 = include_login_entering_1.findViewById<TextView>(R.id.header_login_1)
        header_1.text = getString(R.string.activity_login_header)
        val header_2 = include_login_entering_2.findViewById<TextView>(R.id.header_login_2)
        header_2.text = getString(R.string.activity_login_header)
        val header_3 = include_or_enter_by.findViewById<TextView>(R.id.header_login_3)
        header_3.text = getString(R.string.or_enter_by_header)

        val input_1 = include_login_entering_1.findViewById<FrameLayout>(R.id.input_login_1)
        val input_1_text = input_1.findViewById<EditText>(R.id.input_text)
        input_1_text.hint = getString(R.string.email)

        val input_2 = include_login_entering_1.findViewById<FrameLayout>(R.id.input_login_2)
        val input_2_text = input_2.findViewById<EditText>(R.id.input_text)
        input_2_text.hint = getString(R.string.password)

        //val button_1 = include_login_entering_1.findViewById<ConstraintLayout>(R.id.button_login_1)
        */


        supportFragmentManager.commit {
            val get_login_password = GetLoginPassword(){
                supportFragmentManager.commit{
                    val get_code = GetCode(){
                        TODO("After code code")
                    }
                    replace(R.id.main_login_fragment, get_code)
                }
            }
            replace(R.id.main_login_fragment, get_login_password)
        }



        /*
        button_1.setOnClickListener {




            include_login_entering_1.visibility = View.GONE
            include_login_entering_2.visibility = View.VISIBLE



            val input_3 = include_login_entering_2.findViewById<FrameLayout>(R.id.input_login_3)
            val input_3_text = input_3.findViewById<EditText>(R.id.input_text)
            input_3_text.hint = getString(R.string.code)
            /*
            val button_2 = include_login_entering_2.findViewById<ConstraintLayout>(R.id.button_login_2)
            button_2.setOnClickListener{

            }

             */
        }
        *
         */
    }

}