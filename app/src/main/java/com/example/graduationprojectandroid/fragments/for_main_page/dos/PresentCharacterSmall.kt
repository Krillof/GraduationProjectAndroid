package com.example.graduationprojectandroid.fragments.for_main_page.dos

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.databinding.FragmentPresentCharacterSmallBinding
import com.example.graduationprojectandroid.fragments.Header
import com.example.graduationprojectandroid.network.CharacterData

private const val ARG_PARAM_LOGIN = "login"
private const val ARG_PARAM_MAX_HEALTH = "max_health"
private const val ARG_PARAM_HEALTH = "health"
private const val ARG_PARAM_MAX_EXPERIENCE = "max_experience"
private const val ARG_PARAM_EXPERIENCE = "experience"
private const val ARG_PARAM_LEVEL = "level"

/**
 * A simple [Fragment] subclass.
 * Use the [PresentCharacterSmall.newInstance] factory method to
 * create an instance of this fragment.
 */
class PresentCharacterSmall(
    private val data: CharacterData
) : Fragment() {

    private var max_health: Float = data.max_health.toFloat()
    private var health: Float = data.health.toFloat()
    private var max_experience: Float = data.max_exp.toFloat()
    private var experience: Float = data.exp.toFloat()
    private var level: Int = data.level
    private var money: Int = data.money

    private lateinit var binding: FragmentPresentCharacterSmallBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPresentCharacterSmallBinding.inflate(layoutInflater)
        return binding.root
    }


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
     = with(binding){
        super.onViewCreated(view, savedInstanceState)

        fragmentManager?.commit{
            val header =
                Header.newInstance(
                    data.avatar_name
                )

            add(R.id.header, header)

        }

        val layoutParamsHp = healthBarRect.layoutParams
        layoutParamsHp.width = (layoutParamsHp.width * (health/max_health)).toInt()
        healthBarRect.layoutParams = layoutParamsHp

        healthText.text = health.toInt().toString() + "/" + max_health.toInt().toString()

        val layoutParamsExp = expBarRect.layoutParams
        layoutParamsExp.width = (layoutParamsExp.width * (experience/max_experience)).toInt()
        expBarRect.layoutParams = layoutParamsExp

        expText.text = experience.toInt().toString() + "/" + max_experience.toInt().toString()

        levelText.text = getString(R.string.level) + " " + level

        moneyText.text = money.toString()


    }

    companion object {
        @JvmStatic
        fun newInstance(
            characterData: CharacterData
            ) =
                PresentCharacterSmall(
                    characterData
                )
    }
}