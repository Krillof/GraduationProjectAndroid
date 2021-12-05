package com.example.graduationprojectandroid.fragments.for_main_page.dos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.fragments.Header

private const val ARG_PARAM_LOGIN = "login"
private const val ARG_PARAM_HEALTH = "health"
private const val ARG_PARAM_EXPERIENCE = "experience"
private const val ARG_PARAM_LEVEL = "level"

/**
 * A simple [Fragment] subclass.
 * Use the [PresentCharacterSmall.newInstance] factory method to
 * create an instance of this fragment.
 */
class PresentCharacterSmall : Fragment() {

    private var login: String? = null
    private var health: Int? = null
    private var experience: Int? = null
    private var level: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            login = it.getString(ARG_PARAM_LOGIN)
            health = it.getInt(ARG_PARAM_HEALTH)
            experience = it.getInt(ARG_PARAM_EXPERIENCE)
            level = it.getInt(ARG_PARAM_LEVEL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_present_character_small, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentManager?.commit{
            val header = login?.let {
                Header.newInstance(
                    it
                )
            }
            if (header != null) {
                add(R.id.header, header)
            }



        }
    }

    companion object {
        @JvmStatic
        fun newInstance(login: String, health: Int, experience: Int, level: Int) =
            PresentCharacterSmall().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM_LOGIN, login)
                    putInt(ARG_PARAM_HEALTH, health)
                    putInt(ARG_PARAM_EXPERIENCE, experience)
                    putInt(ARG_PARAM_LEVEL, level)
                }
            }
    }
}