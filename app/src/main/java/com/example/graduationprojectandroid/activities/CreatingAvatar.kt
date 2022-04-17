package com.example.graduationprojectandroid.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.fragments.Button
import com.example.graduationprojectandroid.fragments.for_creating_avatar.AvatarGivingNameMenu
import com.example.graduationprojectandroid.fragments.for_creating_avatar.AvatarParts
import com.example.graduationprojectandroid.fragments.for_creating_avatar.AvatarPartsChoiceMenu
import com.example.graduationprojectandroid.fragments.for_main_page.InfoDialogue
import com.example.graduationprojectandroid.network.DataService

class CreatingAvatar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creating_avatar)

        val context = this

        val maybe_login = intent.extras?.get(LoginActivity.LOGIN) as String?
        val maybe_password = intent.extras?.get(LoginActivity.PASSWORD) as String?

        val body_rectangle = findViewById<ImageView>(R.id.body_rectangle)
        val hair_rectangle = findViewById<ImageView>(R.id.hair_rectangle)
        val background_rectangle = findViewById<ImageView>(R.id.background_rectangle)

        val avatar_parts_rectangle = arrayOf(body_rectangle, hair_rectangle, background_rectangle)
        val chosenParts: Array<Int> = Array(avatar_parts_rectangle.size){ 1 }

        val isRegistering =
                    if (maybe_login == null && maybe_password == null
                        || maybe_login != null && maybe_password != null)
                        maybe_login != null && maybe_password != null
                    else
                        throw Exception("CreatingAvatar: Where is login or password?")

        val ch_dt =
            if (isRegistering)
                DataService.getNewCharacterData(maybe_login!!)
            else
                DataService.getCharacterData()


        //load from server first avatar parts pictures

        avatar_parts_rectangle.forEachIndexed { index, imageView ->
            DataService
                .getAvatarPartPictureForWearing(
                    imageView,
                    AvatarParts.values()[index],
                    1
                );
        }


        // listener for choosing avatar parts
        val avatarPartsChoiceMenu = AvatarPartsChoiceMenu.newInstance(chosenParts, ch_dt,
            {
            val avatarGivingNameMenu = AvatarGivingNameMenu.newInstance(ch_dt)

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment,avatarGivingNameMenu)
                .replace(R.id.button_next,
                    Button.newInstance(getString(R.string.next)){
                        ch_dt.avatar_name = avatarGivingNameMenu.getName()
                        DataService.checkAvatarName {
                            if (it == ""){
                                if (isRegistering) {
                                    //TODO: get numbers of avatar parts pictures from SERVER - problem with CharacterData
                                    DataService.registerUser(maybe_password!!) {
                                        MainPage.currentState = MainPage.MainPageStates.DOS
                                        startActivity(Intent(context, MainPage::class.java))
                                        finish()
                                    }
                                } else {
                                    DataService.updateCharacterData {
                                        MainPage.currentState = MainPage.MainPageStates.DOS
                                        startActivity(Intent(context, MainPage::class.java))
                                        finish()
                                    }
                                }
                            } else {
                                var infoDialogue: InfoDialogue? = null
                                infoDialogue = InfoDialogue.newInstance(it){
                                    //it's ok that "it" isn't in use - program just informing user
                                    infoDialogue?.dismissAllowingStateLoss()
                                }
                                infoDialogue.show(supportFragmentManager, "info_dialogue")
                            }
                        }

                    })
                .commit()
        },
            { switchedAvatarPart ->

                DataService
                    .getAvatarPartPictureForWearing(
                        avatar_parts_rectangle[switchedAvatarPart.number],
                        switchedAvatarPart,
                        chosenParts[switchedAvatarPart.number]
                    );
            }
        );

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment, avatarPartsChoiceMenu)
            .add(R.id.button_next,
                Button.newInstance(getString(R.string.next)){
                    avatarPartsChoiceMenu.clickButtonListener()
                })
            .commit()
    }
}