package com.example.graduationprojectandroid.fragments.for_main_page.dos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.graduationprojectandroid.R


/**
 * A simple [Fragment] subclass.
 * Use the [PresentCharacterBig.newInstance] factory method to
 * create an instance of this fragment.
 */
class PresentCharacterBig : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_present_character_big, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //TODO: Сделать здоровье, опыт, предметы и т.д. (их отображение здесь)
    }

    companion object {
        //TODO: точно нужно передать хотябы логин
        @JvmStatic
        fun newInstance() =
            PresentCharacterBig()
    }
}