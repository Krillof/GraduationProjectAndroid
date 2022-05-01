package com.example.graduationprojectandroid.network


class UserData(
    val login: String,
    var avatar_name: String,

    val money: Int,

    val health: Int,
    val max_health: Int,

    val exp: Int,
    val max_exp: Int,
    val level: Int,

    val shirt_id: Int,
    val pants_id: Int,
    var background_id: Int,
    val pet_id: Int,

    // getting not body_part_id from avatarParts table, getting exactly picture_id of it
    var body_part_picture_id: Int,
    var hair_part_picture_id: Int,
    var background_picture_id: Int //for avatar parts
) {
    fun getPartsAsArray() : Array<Int>{
        return arrayOf(body_part_picture_id, hair_part_picture_id, background_picture_id)
    }
}