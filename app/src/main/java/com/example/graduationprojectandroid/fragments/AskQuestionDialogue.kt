package com.example.graduationprojectandroid.fragments

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import android.graphics.drawable.ColorDrawable
import com.example.graduationprojectandroid.databinding.FragmentAskQuestionDialogueBinding


class AskQuestionDialogue(
    private val text: String,
    private var listener: (answer: Boolean?) -> Unit
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)
        dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    private lateinit var binding: FragmentAskQuestionDialogueBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAskQuestionDialogueBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        textView.text = text

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
        fun newInstance(
            text: String,
            listener: (answer: Boolean?) -> Unit
        ) =
            AskQuestionDialogue(text, listener)
    }
}