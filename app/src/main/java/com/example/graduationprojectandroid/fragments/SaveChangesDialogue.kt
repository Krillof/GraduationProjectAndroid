package com.example.graduationprojectandroid.fragments

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.databinding.FragmentHabitsListBinding
import com.example.graduationprojectandroid.databinding.FragmentSaveChangesDialogueBinding
import android.graphics.drawable.ColorDrawable

import androidx.annotation.NonNull





private const val ARG_PARAM_LISTENER = "listener"

class SaveChangesDialogue(
    private var listener: (answer: Boolean?) -> Unit
) : DialogFragment() {

    //TODO: Подправить код по примеру здесь

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)
        dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

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