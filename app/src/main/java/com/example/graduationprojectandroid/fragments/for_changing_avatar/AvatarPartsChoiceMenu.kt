package com.example.graduationprojectandroid.fragments.for_changing_avatar

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.graduationprojectandroid.databinding.FragmentAvatarPartsChoiceMenuBinding
import com.example.graduationprojectandroid.network.DataService
import com.example.graduationprojectandroid.network.UserData

/**
 * A simple [Fragment] subclass.
 * Use the [AvatarPartsChoiceMenu.newInstance] factory method to
 * create an instance of this fragment.
 */
class AvatarPartsChoiceMenu(
    private var chosenParts: Array<Int>,
    private val listener_for_next_fragment: ()->Unit,
    private val listener_for_avatar_parts: (ap: AvatarParts) -> Unit
) : Fragment() {

    private lateinit var binding: FragmentAvatarPartsChoiceMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAvatarPartsChoiceMenuBinding.inflate(layoutInflater)

        return binding.root
    }



    private fun updatePage(page: AvatarParts) = with(binding){
        currentAvatarPartPage = page

        val choices = listOf(choice1, choice2, choice3)

        DataService.getAmountOfOneAvatarPartType(currentAvatarPartPage){ amount ->


            avatarPartsList.layoutManager = GridLayoutManager(avatarPartsList.context, 5)
            avatarPartsList.adapter = AvatarPartsAdapter(
                amount, currentAvatarPartPage.number, chosenParts[currentAvatarPartPage.number]
            ) { id, changeChosen -> switchVariant(id, changeChosen) }
            //TODO: remove "skin_color..." and etc. - we will get them from server
            /*

                    variants[0].setBackgroundResource(R.drawable.skin_color_rect_1)
                    variants[1].setBackgroundResource(R.drawable.skin_color_rect_2)
                    variants[2].setBackgroundResource(R.drawable.skin_color_rect_3)
                    variants[3].setBackgroundResource(R.drawable.skin_color_rect_4)
                    variants[4].setBackgroundResource(R.drawable.skin_color_rect_5)
                    variants[5].setBackgroundResource(R.drawable.skin_color_rect_6)
                    variants[6].setBackgroundResource(R.drawable.skin_color_rect_7)
                    variants[7].setBackgroundResource(R.drawable.skin_color_rect_8)
                    variants[8].setBackgroundResource(R.drawable.skin_color_rect_9)
                    variants[9].setBackgroundResource(R.drawable.skin_color_rect_10)

                    for (i in 1..3){
                        variants[i-1].setBackgroundResource(R.drawable.white_rect_for_hair)
                    }
                    //TMP


                    for (i in 1..5){
                        variants[i-1].setBackgroundResource(R.drawable.market_item_background)
                    }
                    //TMP

                     */

        }


        choices.forEach {
            it.visibility = View.GONE
        }
        choices[currentAvatarPartPage.number].visibility = View.VISIBLE
        switchVariant(chosenParts[currentAvatarPartPage.number])
    }

    private fun switchVariant(id: Int, changeChosen: (Int)->Unit = {}) = with(binding)
    {

        chosenParts[currentAvatarPartPage.number] = id
        changeChosen(id)

        listener_for_avatar_parts(currentAvatarPartPage)
    }

    fun clickButtonListener(){
        if (currentAvatarPartPage.number < chosenParts.size-1){
            updatePage(AvatarParts.values().first {
                currentAvatarPartPage.number+1 == it.number
            })
        } else {
            listener_for_next_fragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit
    = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        val choice_texts = listOf(choiceText1, choiceText2, choiceText3)

        choice_texts.forEachIndexed { i, view ->
            view.setOnClickListener {
                updatePage(AvatarParts.values().first { i == it.number })
            }
        }

        choice_texts[AvatarParts.BODY.number].performClick()
    }

    companion object {

        var currentAvatarPartPage: AvatarParts = AvatarParts.BODY

        @JvmStatic
        fun newInstance(chosenParts: Array<Int>,
                        listener_for_next_fragment: ()->Unit,
                        listener_for_avatar_parts: (AvatarParts)->Unit
        )
        : AvatarPartsChoiceMenu {
            return AvatarPartsChoiceMenu(
                chosenParts,
                {listener_for_next_fragment()},
                {listener_for_avatar_parts(it)}
            );
        }
    }
}