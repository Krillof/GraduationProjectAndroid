package com.example.graduationprojectandroid.fragments.for_main_page.inventory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.databinding.FragmentInventoryBinding
import com.example.graduationprojectandroid.fragments.for_main_page.dos.MarketList
import com.example.graduationprojectandroid.fragments.for_main_page.dos.PresentCharacterBig
import com.example.graduationprojectandroid.network.DataService

/**
 * A simple [Fragment] subclass.
 * Use the [Inventory.newInstance] factory method to
 * create an instance of this fragment.
 */
class Inventory : Fragment() {

    private lateinit var binding: FragmentInventoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInventoryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit
        = with(binding){
        super.onViewCreated(view, savedInstanceState)

        val characterData = DataService.getCharacterData()

        fragmentManager?.commit{
            val presentCharacterBig
                    = PresentCharacterBig.newInstance(
                characterData
            )

            val inventoryList
                    = InventoryList.newInstance(characterData.money)



            replace(R.id.first_fragment, presentCharacterBig)
            replace(R.id.second_fragment, inventoryList)

        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            Inventory()
    }
}