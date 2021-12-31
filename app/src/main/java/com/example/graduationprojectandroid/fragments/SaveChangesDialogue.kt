package com.example.graduationprojectandroid.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.databinding.FragmentHabitsListBinding
import com.example.graduationprojectandroid.databinding.FragmentSaveChangesDialogueBinding


private const val ARG_PARAM_LISTENER = "listener"

class SaveChangesDialogue(
    private var listener: (answer: Boolean?) -> Unit
) : Fragment() {

    //TODO: Подправить код по примеру здесь

    private lateinit var binding: FragmentSaveChangesDialogueBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSaveChangesDialogueBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    = with(binding) {                                                       //TODO: Этот пример
        super.onViewCreated(view, savedInstanceState)
        closeButton.setOnClickListener {
            listener(null)
        }

        yesButton.setOnClickListener {
            listener(true)
        }

        noButton.setOnClickListener {
            listener(false)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(listener: (answer: Boolean?) -> Unit) =
            SaveChangesDialogue(listener)
    }
}