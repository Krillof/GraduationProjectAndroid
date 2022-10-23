package com.example.graduationprojectandroid.data.Items

import java.io.Serializable

class NewsItem(
    val id: Int,
    val date: String,
    val header: String,
    val info_text: String,
    val main_text: String
) : Serializable {}