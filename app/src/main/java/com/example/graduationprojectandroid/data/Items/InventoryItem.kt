package com.example.graduationprojectandroid.data.Items

import android.view.View

class InventoryItem(
     val id: Int,
     val picture_id: Int,
     val money: Int,
     val plus_hp: Int = 0,
     val plus_exp: Int = 0,
     val visibility: Int = View.VISIBLE
) {
     constructor(id: Int, picture_id: Int, money: Int)
             : this(id, picture_id, money, 0 , 0, View.VISIBLE) {}
}