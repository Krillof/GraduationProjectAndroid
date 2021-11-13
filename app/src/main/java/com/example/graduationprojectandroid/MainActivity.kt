package com.example.graduationprojectandroid

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.graduationprojectandroid.network.NetworkService

import com.example.graduationprojectandroid.network.endpoints.Test
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.content.Intent




class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Проверка на то, что мы залогинены
        val entered : Boolean = false

        if (entered){

        } else {
            //Запускаем активити для логина
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }



        /*

        val text1 = findViewById<TextView>(R.id.text1)
        val button = findViewById<Button>(R.id.button)


        button.setOnClickListener {
            NetworkService
                .getInstance()
                .jsonApi
                .test()
                .enqueue(object : Callback<Test> {

                    @SuppressLint("SetTextI18n")
                    override fun onResponse(call: Call<Test>, response: Response<Test>) {
                        val post = response.body()

                        text1.text = post?.id.toString() +  "\n"
                        text1.text = text1.text.toString() + post?.str.toString() +  "\n"
                        text1.text = text1.text.toString() + post?.time.toString() +  "\n"
                    }

                    override fun onFailure(call: Call<Test>, t: Throwable) {
                        text1.text = "Error during calling: " + t.message;
                    }

                })
        }

         */

    }
}