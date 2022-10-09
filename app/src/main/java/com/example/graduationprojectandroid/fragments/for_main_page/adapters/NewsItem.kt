package com.example.graduationprojectandroid.fragments.for_main_page.adapters

import java.io.Serializable

class NewsItem(
    val id: Int,
    val date: String,
    val header: String,
    val info_text: String,
    val main_text: String
) : Serializable {}