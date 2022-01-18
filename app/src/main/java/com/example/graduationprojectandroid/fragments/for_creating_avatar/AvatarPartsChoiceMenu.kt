package com.example.graduationprojectandroid.fragments.for_creating_avatar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.databinding.FragmentAvatarPartsChoiceMenuBinding
import com.example.graduationprojectandroid.network.CharacterData

/**
 * A simple [Fragment] subclass.
 * Use the [AvatarPartsChoiceMenu.newInstance] factory method to
 * create an instance of this fragment.
 */
class AvatarPartsChoiceMenu(
    private var characterData: CharacterData,
    private val listener: ()->Unit
) : Fragment() {

    enum class AvatarPartsPages(var number: Int){
        BODY(0),
        HAIR(1),
        BACKGROUND(2)
    }

    private val AVATAR_PARTS_AMOUNT = 3

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

    private fun updatePage(page: AvatarPartsPages) = with(binding){
        currentAvatarPartPage = page

        val choices = listOf(choice1, choice2, choice3)

        val variants = listOf(variant1, variant2, variant3, variant4, variant5,
            variant6, variant7, variant8, variant9, variant10)

        when(currentAvatarPartPage){
            AvatarPartsPages.BODY ->{
                for (i in 1..10){
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

            AvatarPartsPages.HAIR ->{
                for (i in 1..3){
                    variants[i-1].visibility = View.VISIBLE
                }
                for (i in 4..10){
                    variants[i-1].visibility = View.GONE
                }

                //0 is special
                variants[0].setBackgroundResource(R.drawable.color_hair_choose_button)

                for (i in 2..3){

                    variants[i-1].setBackgroundResource(R.drawable.white_rect_for_hair)
                }
            }

            AvatarPartsPages.BACKGROUND->{
                for (i in 1..5){
                    variants[i-1].visibility = View.VISIBLE
                }
                for (i in 6..10){
                    variants[i-1].visibility = View.GONE
                }

                for (i in 1..5){
                    variants[i-1].setBackgroundResource(R.drawable.market_item_background)
                }
            }
        }

        choices.forEach {
            it.visibility = View.GONE
        }
        choices[currentAvatarPartPage.number].visibility = View.VISIBLE
        switchVariant(choosenPart[currentAvatarPartPage.number])
    }

    private fun switchVariant(index: Int) = with(binding)
    {
        if (currentAvatarPartPage == AvatarPartsPages.HAIR
            && index == 0){
            return
        }

        val variantBorders = listOf(variant1Border, variant2Border,
            variant3Border, variant4Border, variant5Border,
            variant6Border, variant7Border, variant8Border,
            variant9Border, variant10Border)

        variantBorders.forEach {
            it.visibility = View.GONE
        }

        choosenPart[currentAvatarPartPage.number] = index
        variantBorders[index].visibility = View.VISIBLE
    }

    fun clickButtonListener(){
        if (currentAvatarPartPage.number < AVATAR_PARTS_AMOUNT-1){
            updatePage(AvatarPartsPages.values().first {
                currentAvatarPartPage.number+1 == it.number
            })
        } else {
            characterData.body_part = choosenPart[0]
            characterData.hair_part = choosenPart[1] - 1
            characterData.hair_part_color = hairColorPart
            characterData.background_id = choosenPart[2]
            listener()
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
                updatePage(AvatarPartsPages.values().first { i == it.number })
            }
        }

        choice_texts[AvatarPartsPages.BODY.number].performClick()
    }

    companion object {

        var currentAvatarPartPage: AvatarPartsPages = AvatarPartsPages.BODY
        var choosenPart: Array<Int> = arrayOf(1, 1, 0)
        var hairColorPart: Int = 0

        @JvmStatic
        fun newInstance(characterData: CharacterData, listener: ()->Unit)
        : AvatarPartsChoiceMenu {
            currentAvatarPartPage = AvatarPartsPages.BODY
            choosenPart = arrayOf(1, 1, 0)
            hairColorPart = 0


            return AvatarPartsChoiceMenu(characterData) {listener()}
        }
    }
}