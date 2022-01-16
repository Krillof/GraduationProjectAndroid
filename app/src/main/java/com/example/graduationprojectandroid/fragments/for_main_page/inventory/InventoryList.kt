package com.example.graduationprojectandroid.fragments.for_main_page.inventory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.databinding.FragmentInventoryListBinding
import com.example.graduationprojectandroid.databinding.FragmentMarketListBinding
import com.example.graduationprojectandroid.fragments.for_main_page.adapters.InventoryItemsAdapter
import com.example.graduationprojectandroid.fragments.for_main_page.adapters.MarketItem
import com.example.graduationprojectandroid.fragments.for_main_page.adapters.MarketItemsAdapter
import com.example.graduationprojectandroid.network.NetworkService

/**
 * A simple [Fragment] subclass.
 * Use the [MarketList.newInstance] factory method to
 * create an instance of this fragment.
 */
class InventoryList(
    private val money: Int
) : Fragment() {

    private lateinit var binding: FragmentInventoryListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInventoryListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)= with(binding) {
        super.onViewCreated(view, savedInstanceState)
        inventoryList.layoutManager = GridLayoutManager(inventoryList.context, 3)
        inventoryList.adapter = InventoryItemsAdapter(parentFragmentManager, getInventoryItems())
    }

    private fun getInventoryItems(): ArrayList<MarketItem>{
        val items = ArrayList<MarketItem>()

        items.addAll(NetworkService.getInstance().itemsForInventory)

        for (i in 0..15){
            items.add(MarketItem(0, i, 0, 0,  View.INVISIBLE))
        }

        return items
    }

    companion object {
        @JvmStatic
        fun newInstance(money: Int) =
            InventoryList(money)
    }
}
