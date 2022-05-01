package com.example.graduationprojectandroid.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.fragments.Button
import com.example.graduationprojectandroid.fragments.for_changing_avatar.AvatarGivingNameMenu
import com.example.graduationprojectandroid.fragments.for_changing_avatar.AvatarParts
import com.example.graduationprojectandroid.fragments.for_changing_avatar.AvatarPartsChoiceMenu
import com.example.graduationprojectandroid.fragments.for_main_page.InfoDialogue
import com.example.graduationprojectandroid.network.DataService

class ChangingAvatar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_changing_avatar)

        val context = this
        // TODO: go to xml and make under button a rectangle for not showing under button avatar parts if they will get under it
        val body_rectangle = findViewById<ImageView>(R.id.body_rectangle)
        val hair_rectangle = findViewById<ImageView>(R.id.hair_rectangle)
        val background_rectangle = findViewById<ImageView>(R.id.background_rectangle)

        val avatar_parts_rectangle = arrayOf(body_rectangle, hair_rectangle, background_rectangle)



        //loads from server first avatar parts pictures
        avatar_parts_rectangle.forEachIndexed { index, imageView ->
            DataService
                .getAvatarPartPictureForWearing(
                    imageView,
                    AvatarParts.values()[index],
                    1
                );
        }

        DataService.getUserData { ch_dt ->
            val chosenParts: Array<Int> = ch_dt.getPartsAsArray()

            // listener for choosing avatar parts
            val avatarPartsChoiceMenu = AvatarPartsChoiceMenu.newInstance(chosenParts,
                {
                    val avatarGivingNameMenu = AvatarGivingNameMenu.newInstance(ch_dt)

                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment,avatarGivingNameMenu)
                        .replace(R.id.button_next,
                            Button.newInstance(getString(R.string.next)){
                                DataService.checkAvatarName {
                                    if (it == ""){
                                        DataService.changedAvatar(chosenParts,
                                            avatarGivingNameMenu.getName())
                                        {
                                            MainPage.currentState = MainPage.MainPageStates.DOS
                                            startActivity(Intent(context, MainPage::class.java))
                                            finish()
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
}