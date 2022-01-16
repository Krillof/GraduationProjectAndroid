package com.example.graduationprojectandroid.fragments.for_main_page.dos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.databinding.FragmentMarketListBinding
import com.example.graduationprojectandroid.databinding.FragmentPresentCharacterBigBinding
import com.example.graduationprojectandroid.network.CharacterData


/**
 * A simple [Fragment] subclass.
 * Use the [PresentCharacterBig.newInstance] factory method to
 * create an instance of this fragment.
 */
class PresentCharacterBig(
    private val data: CharacterData
) : Fragment() {

    private lateinit var binding: FragmentPresentCharacterBigBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPresentCharacterBigBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
        = with(binding)  {
        super.onViewCreated(view, savedInstanceState)
        header.text = data.avatar_name

        val health: Float = data.health.toFloat()
        val max_health: Float = data.max_health.toFloat()
        val exp: Float = data.exp.toFloat()
        val max_exp: Float = data.max_exp.toFloat()

        val layoutParamsHp = healthBarRect.layoutParams
        layoutParamsHp.width = (layoutParamsHp.width * (health/max_health)).toInt()
        healthBarRect.layoutParams = layoutParamsHp

        val layoutParamsExp = expBarRect.layoutParams
        layoutParamsExp.width = (layoutParamsExp.width * (exp/max_exp)).toInt()
        expBarRect.layoutParams = layoutParamsExp
    }

    companion object {

        @JvmStatic
        fun newInstance(characterData: CharacterData) =
            PresentCharacterBig(characterData)
    }
}