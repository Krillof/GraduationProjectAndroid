package com.example.graduationprojectandroid.fragments.for_main_page

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.graduationprojectandroid.databinding.FragmentInfoDialogueBinding

class InfoDialogue(
    private val text: String,
    private var listener: (answer: Boolean) -> Unit
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)
        dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    private lateinit var binding: FragmentInfoDialogueBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoDialogueBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
            = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        textView.text = text

        closeButton.setOnClickListener {
            listener(false)
        }

        okButton.setOnClickListener {
            listener(true)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(
            text: String,
            listener: (answer: Boolean) -> Unit
        ) =
            InfoDialogue(text, listener)
    }
}