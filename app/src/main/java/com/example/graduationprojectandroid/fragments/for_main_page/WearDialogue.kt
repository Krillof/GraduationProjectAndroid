package com.example.graduationprojectandroid.fragments.for_main_page

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.databinding.SimpleLayoutWearItemBinding
import com.example.graduationprojectandroid.data.Items.InventoryItem
import com.example.graduationprojectandroid.network.DataService

class WearDialogue (
    private var inventoryItem: InventoryItem,
    private var listener: (answer: Boolean) -> Unit
) : DialogFragment() {

    //TODO: Подправить код по примеру здесь

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)
        dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    private lateinit var binding: SimpleLayoutWearItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SimpleLayoutWearItemBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
            = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        val text_part1 = getString(R.string.health)+" +"+inventoryItem.plus_hp.toString()+"\n"
        val text_part2 = getString(R.string.experience) + " +" + inventoryItem.plus_exp.toString()
        infoText.text = text_part1 + text_part2

        DataService.setPictureById(inventoryItem.id, picture)

        closeButton.setOnClickListener {
            listener(false)
        }

        buyButton.setOnClickListener {
            listener(true)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(
            inventoryItem: InventoryItem,
            listener: (answer: Boolean) -> Unit
        ) =
            BuyDialogue(inventoryItem, listener)
    }
}