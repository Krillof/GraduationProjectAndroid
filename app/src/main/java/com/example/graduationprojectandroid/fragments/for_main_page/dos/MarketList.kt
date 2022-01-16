package com.example.graduationprojectandroid.fragments.for_main_page.dos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.graduationprojectandroid.databinding.FragmentMarketListBinding
import com.example.graduationprojectandroid.fragments.for_main_page.adapters.MarketItem
import com.example.graduationprojectandroid.fragments.for_main_page.adapters.MarketItemsAdapter
import com.example.graduationprojectandroid.network.NetworkService


/**
 * A simple [Fragment] subclass.
 * Use the [MarketList.newInstance] factory method to
 * create an instance of this fragment.
 */
class MarketList(
    private val money: Int
) : Fragment() {

    private lateinit var binding: FragmentMarketListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMarketListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)= with(binding) {
        super.onViewCreated(view, savedInstanceState)
        marketItemsList.layoutManager = GridLayoutManager(marketItemsList.context, 3)
        marketItemsList.adapter = MarketItemsAdapter(parentFragmentManager, getMarketItems())


        moneyText.text = money.toString()
    }

    private fun getMarketItems(): ArrayList<MarketItem>{
        val items = ArrayList<MarketItem>()

        items.addAll(NetworkService.getInstance().itemsForMarket)

        for (i in 0..15){
            items.add(MarketItem(0, i, 0, 0,  View.INVISIBLE))
        }

        return items
    }

    companion object {
        @JvmStatic
        fun newInstance(money: Int) =
            MarketList(money)
    }
}