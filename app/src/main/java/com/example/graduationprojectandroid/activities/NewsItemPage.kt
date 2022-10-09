package com.example.graduationprojectandroid.activities

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.databinding.ActivityNewsItemPageBinding
import com.example.graduationprojectandroid.network.DataService

class NewsItemPage : AppCompatActivity() {

    private lateinit var binding: ActivityNewsItemPageBinding

    companion object {
        const val IS_GOT_FROM_READING_NEWS_ITEM = "READING_NEWS_ITEM"
    }

    private fun getVerticalTextSpace(size: Int): String{
        var s: String = ""
        for (i in 0..size){
            s += "\n"
        }
        return s
    }

    private fun goBack(){
        val intent = Intent(this, MainPage::class.java)
        intent.putExtra(IS_GOT_FROM_READING_NEWS_ITEM, true)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        goBack()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val newsItemId = intent.extras?.getInt(MainPage.ARG_NEWS_ITEM_ID)
        if (newsItemId == null){
            goBack()
        } else {
            DataService.getNewsItemById(newsItemId) {
                binding = ActivityNewsItemPageBinding.inflate(layoutInflater)
                setContentView(binding.root)

                binding.date.text = it.date
                binding.header.text = it.header
                binding.infoText.text = it.info_text
                binding.mainText.text = it.main_text
                binding.exitButton.setOnClickListener {
                    goBack()
                }
            }
        }


    }
}