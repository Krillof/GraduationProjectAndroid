package com.example.graduationprojectandroid.fragments.for_main_page.dos

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.databinding.FragmentPresentCharacterSmallBinding
import com.example.graduationprojectandroid.fragments.Header

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
class PresentCharacterSmall : Fragment() {

    private var login: String? = null
    private var max_health: Float? = null
    private var health: Float? = null
    private var max_experience: Float? = null
    private var experience: Float? = null
    private var level: Int? = null

    private lateinit var binding: FragmentPresentCharacterSmallBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            login = it.getString(ARG_PARAM_LOGIN)
            max_health = it.getInt(ARG_PARAM_MAX_HEALTH).toFloat()
            health = it.getInt(ARG_PARAM_HEALTH).toFloat()
            max_experience = it.getInt(ARG_PARAM_MAX_EXPERIENCE).toFloat()
            experience = it.getInt(ARG_PARAM_EXPERIENCE).toFloat()
            level = it.getInt(ARG_PARAM_LEVEL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPresentCharacterSmallBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
     = with(binding){
        super.onViewCreated(view, savedInstanceState)

        fragmentManager?.commit{
            val header =
                Header.newInstance(
                    login!!
                )

            if (header != null) {
                add(R.id.header, header)
            }

        }

        var layoutParamsHp = healthBarRect.layoutParams
        layoutParamsHp.width = (layoutParamsHp.width * (health!!/max_health!!)).toInt()
        healthBarRect.layoutParams = layoutParamsHp

        healthText.text = health!!.toInt().toString() + "/" + max_health!!.toInt().toString()

        var layoutParamsExp = expBarRect.layoutParams
        layoutParamsExp.width = (layoutParamsExp.width * (experience!!/max_experience!!)).toInt()
        expBarRect.layoutParams = layoutParamsExp

        expText.text = experience!!.toInt().toString() + "/" + max_experience!!.toInt().toString()
    }

    companion object {
        @JvmStatic
        fun newInstance(
            login: String,
            max_health: Int,
            health: Int,
            max_experience: Int,
            experience: Int,
            level: Int) =
            PresentCharacterSmall().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM_LOGIN, login)
                    putInt(ARG_PARAM_MAX_HEALTH, max_health)
                    putInt(ARG_PARAM_HEALTH, health)
                    putInt(ARG_PARAM_MAX_EXPERIENCE, max_experience)
                    putInt(ARG_PARAM_EXPERIENCE, experience)
                    putInt(ARG_PARAM_LEVEL, level)
                }
            }
    }
}