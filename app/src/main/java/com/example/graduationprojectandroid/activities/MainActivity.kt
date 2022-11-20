package com.example.graduationprojectandroid.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.example.graduationprojectandroid.App
import com.example.graduationprojectandroid.PreferencesService
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.network.DataService


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //TODO: remove
        Toast.makeText(App.getAppContext(), "Toasts are working", Toast.LENGTH_SHORT).show()
        //DataService.test {
        //    Log.println(Log.ERROR, "Checking server connection", it)
        //}


        //val sharedPreferences = App.getAppContext().getSharedPreferences(PreferencesService.SAVING_TOKEN_STR, Context.MODE_PRIVATE)
        //sharedPreferences.edit().clear().commit()

        if (PreferencesService.isTokenExists()) {
            DataService.checkToken() {
                if (it) {
                    startActivity(Intent(this, MainPage::class.java))
                } else {
                    startActivity(Intent(this, LoginActivity::class.java))
                }
                finish()
            }
        } else {
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