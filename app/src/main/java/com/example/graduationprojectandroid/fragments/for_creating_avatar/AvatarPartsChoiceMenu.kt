package com.example.graduationprojectandroid.fragments.for_creating_avatar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.databinding.FragmentAvatarPartsChoiceMenuBinding
import com.example.graduationprojectandroid.network.CharacterData
import com.example.graduationprojectandroid.network.DataService

/**
 * A simple [Fragment] subclass.
 * Use the [AvatarPartsChoiceMenu.newInstance] factory method to
 * create an instance of this fragment.
 */
class AvatarPartsChoiceMenu(
    private var chosenParts: Array<Int>,
    private var characterData: CharacterData,
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

    //TODO: get from SERVER choosing_pictures for avatar parts
    //TODO: think about more than 10 max variants - make list
    private fun updatePage(page: AvatarParts) = with(binding){
        currentAvatarPartPage = page

        val choices = listOf(choice1, choice2, choice3)

        val variants = listOf(variant1, variant2, variant3, variant4, variant5,
            variant6, variant7, variant8, variant9, variant10)

        DataService.getAmountOfOneAvatarPartType(currentAvatarPartPage){ amount ->
            when(currentAvatarPartPage){
                AvatarParts.BODY ->{
                    for (i in 1..amount){
                        variants[i-1].visibility = View.VISIBLE
                    }

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
                }

                AvatarParts.HAIR ->{
                    for (i in 1..amount){
                        variants[i-1].visibility = View.VISIBLE
                    }

                    for (i in 1..3){
                        variants[i-1].setBackgroundResource(R.drawable.white_rect_for_hair)
                    }
                }

                AvatarParts.BACKGROUND->{
                    for (i in 1..amount){
                        variants[i-1].visibility = View.VISIBLE
                    }

                    for (i in 1..5){
                        variants[i-1].setBackgroundResource(R.drawable.market_item_background)
                    }
                }
            }
            for (i in (amount+1)..10){
                variants[i-1].visibility = View.GONE
            }
        }


        choices.forEach {
            it.visibility = View.GONE
        }
        choices[currentAvatarPartPage.number].visibility = View.VISIBLE
        switchVariant(chosenParts[currentAvatarPartPage.number])
    }

    private fun switchVariant(index: Int) = with(binding)
    {
        val variantBorders = listOf(variant1Border, variant2Border,
            variant3Border, variant4Border, variant5Border,
            variant6Border, variant7Border, variant8Border,
            variant9Border, variant10Border)

        variantBorders.forEach {
            it.visibility = View.GONE
        }

        chosenParts[currentAvatarPartPage.number] = index
        variantBorders[index].visibility = View.VISIBLE

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
        val variants = listOf(variant1, variant2, variant3, variant4, variant5,
            variant6, variant7, variant8, variant9, variant10)

        variants.forEachIndexed { index, view ->
            view.setOnClickListener {
                switchVariant(index)
            }
        }

        choice_texts.forEachIndexed { i, view ->
            view.setOnClickListener {
                updatePage(AvatarParts.values().first { i == it.number })
            }
        }

        choice_texts[AvatarParts.BODY.number].performClick()
    }

    companion object {

        var currentAvatarPartPage: AvatarParts = AvatarParts.BODY
        var hairColorPart: Int = 0

        @JvmStatic
        fun newInstance(chosenParts: Array<Int>,
                        characterData: CharacterData,
                        listener_for_next_fragment: ()->Unit,
                        listener_for_avatar_parts: (AvatarParts)->Unit
        )
        : AvatarPartsChoiceMenu {
            currentAvatarPartPage = AvatarParts.BODY
            hairColorPart = 0


            return AvatarPartsChoiceMenu(
                chosenParts,
                characterData,
                {listener_for_next_fragment()},
                {listener_for_avatar_parts(it)}
            );
        }
    }
}