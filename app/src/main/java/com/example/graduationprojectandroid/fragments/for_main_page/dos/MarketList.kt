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
class MarketList : Fragment() {

    //private var param1: String? = null

    private lateinit var binding: FragmentMarketListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMarketListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() = with(binding){
        marketItemsList.layoutManager = GridLayoutManager(marketItemsList.context, 3)
        marketItemsList.adapter = MarketItemsAdapter(getMarketItems())
    }

    private fun getMarketItems(): ArrayList<MarketItem>{
        val items = ArrayList<MarketItem>()

        //---------------------------------------------------
        //TODO: Сделать загрузку предметов из магазина
        items.add(MarketItem(19))
        items.add(MarketItem(21))
        items.add(MarketItem(128))
        items.add(MarketItem(215))
        items.add(MarketItem(15))
        items.add(MarketItem(125))
        items.add(MarketItem(145))
        items.add(MarketItem(155))
        items.add(MarketItem(165))
        items.add(MarketItem(175))
        items.add(MarketItem(185))
        items.add(MarketItem(195))
        items.add(MarketItem(11655))
        items.add(MarketItem(1345))
        items.add(MarketItem(135))
        items.add(MarketItem(165))


        //---------------------------------------------------

        for (i in 0..15){
            items.add(MarketItem(i, View.INVISIBLE))
        }

        return items
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            MarketList().apply {
                arguments = Bundle().apply {
                    //putString(ARG_PARAM1, param1)
                }
            }
    }
}