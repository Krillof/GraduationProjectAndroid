package com.example.graduationprojectandroid.fragments.for_main_page.adapters

import android.view.View

class MarketItem(
    _money: Int,
    _visibility: Int = View.VISIBLE
) {

    private var money: Int = _money
    private var visibility: Int = _visibility

    fun getMoney(): Int = money

    fun getVisibility():Int = visibility
}