package com.example.graduationprojectandroid.fragments.for_main_page.dos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.databinding.FragmentHabitsListBinding
import com.example.graduationprojectandroid.databinding.FragmentMarketListBinding
import com.example.graduationprojectandroid.fragments.for_main_page.adapters.MarketItem
import com.example.graduationprojectandroid.fragments.for_main_page.adapters.MarketItemsAdapter


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

        //---------------------------------------------------
        //TODO: Сделать загрузку предметов из магазина
        items.add(MarketItem(1, 19, 10, 15))
        items.add(MarketItem(2, 21, 3, 5))
        items.add(MarketItem(3, 128))
        items.add(MarketItem(1, 215))
        items.add(MarketItem(2,15))
        items.add(MarketItem(3,125))
        items.add(MarketItem(1,145))
        items.add(MarketItem(2,155))
        items.add(MarketItem(3,165))
        items.add(MarketItem(1,175))
        items.add(MarketItem(2,185))
        items.add(MarketItem(3,195))
        items.add(MarketItem(1,11655))
        items.add(MarketItem(2,1345))
        items.add(MarketItem(3,135))
        items.add(MarketItem(1,165))
        items.add(MarketItem(1, 19, 10, 15))
        items.add(MarketItem(2, 21, 3, 5))
        items.add(MarketItem(3, 128))
        items.add(MarketItem(1, 215))
        items.add(MarketItem(2,15))
        items.add(MarketItem(3,125))
        items.add(MarketItem(1,145))
        items.add(MarketItem(2,155))
        items.add(MarketItem(3,165))
        items.add(MarketItem(1,175))
        items.add(MarketItem(2,185))
        items.add(MarketItem(3,195))
        items.add(MarketItem(1,11655))
        items.add(MarketItem(2,1345))
        items.add(MarketItem(3,135))
        items.add(MarketItem(1,165))


        //---------------------------------------------------

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