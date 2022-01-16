package com.example.graduationprojectandroid.fragments.for_creating_avatar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.databinding.FragmentAvatarGivingNameMenuBinding
import com.example.graduationprojectandroid.databinding.FragmentAvatarPartsChoiceMenuBinding
import com.example.graduationprojectandroid.network.CharacterData


/**
 * A simple [Fragment] subclass.
 * Use the [AvatarGivingNameMenu.newInstance] factory method to
 * create an instance of this fragment.
 */
class AvatarGivingNameMenu(
    private val characterData: CharacterData
) : Fragment() {

    private lateinit var binding: FragmentAvatarGivingNameMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAvatarGivingNameMenuBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    = with(binding){
        super.onViewCreated(view, savedInstanceState)

        val name_input = view.findViewById<View>(R.id.name_input)
        //because it didn't work with nameInput from binding

        blueInputBox.setOnClickListener {
            name_input.requestFocus()
        }
    }

    fun getName() : String = with(binding){
        return nameInput.text.toString()
    }

    companion object {
        @JvmStatic
        fun newInstance(characterData: CharacterData) =
            AvatarGivingNameMenu(characterData)

    }
}