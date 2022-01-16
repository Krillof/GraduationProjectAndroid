package com.example.graduationprojectandroid.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.fragments.Button
import com.example.graduationprojectandroid.fragments.for_creating_avatar.AvatarGivingNameMenu
import com.example.graduationprojectandroid.fragments.for_creating_avatar.AvatarPartsChoiceMenu
import com.example.graduationprojectandroid.network.CharacterData
import com.example.graduationprojectandroid.network.DataService

class CreatingAvatar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creating_avatar)

        val dataService = DataService.getDataService()
        var ch_dt = dataService.getCharacterData()

        val avatarPartsChoiceMenu =  AvatarPartsChoiceMenu.newInstance(ch_dt){

            val avatarGivingNameMenu = AvatarGivingNameMenu.newInstance(ch_dt)

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment,avatarGivingNameMenu)
                .replace(R.id.button_next,
                    Button.newInstance(getString(R.string.next)){
                        dataService.tryToUpdateCharacterData()
                        avatarGivingNameMenu.getName()
                    })
                .commit()
        }

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment, avatarPartsChoiceMenu)
            .add(R.id.button_next,
                Button.newInstance(getString(R.string.next)){
                    avatarPartsChoiceMenu.clickButtonListener()
                })
            .commit()
    }
}