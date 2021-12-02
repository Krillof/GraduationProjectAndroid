package com.example.graduationprojectandroid.fragments.for_main_page.adapters

data class Task(
    val id: Int,
    val header: String,
    val text: String,
    val done: List<Boolean>,
    val subtasks: List<String>,
    val invisible: Boolean = false
    )