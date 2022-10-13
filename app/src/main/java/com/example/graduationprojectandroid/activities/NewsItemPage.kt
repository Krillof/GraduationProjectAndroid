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
        const val ARG_NEWS_ITEM_ID = "news_item"
    }

    override fun onBackPressed() {
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val newsItemId = intent.extras?.getInt(ARG_NEWS_ITEM_ID)
        if (newsItemId == null){
            finish()
        } else {
            DataService.getNewsItemById(newsItemId) {
                binding = ActivityNewsItemPageBinding.inflate(layoutInflater)
                setContentView(binding.root)

                binding.date.text = it.date
                binding.header.text = it.header
                binding.infoText.text = it.info_text
                binding.mainText.text = it.main_text
                binding.exitButton.setOnClickListener {
                    finish()
                }
            }
        }


    }
}