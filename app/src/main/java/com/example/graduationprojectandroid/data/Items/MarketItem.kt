package com.example.graduationprojectandroid.data.Items

import android.view.View

class MarketItem(
     val id: Int,
     val money: Int,
     val plus_hp: Int = 0,
     val plus_exp: Int = 0,
     val visibility: Int = View.VISIBLE
) {
     constructor(id: Int, money: Int)
             : this(id, money, 0 , 0, View.VISIBLE) {}
}