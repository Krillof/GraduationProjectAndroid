package com.example.graduationprojectandroid.fragments.for_main_page.dos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.graduationprojectandroid.databinding.FragmentMarketListBinding
import com.example.graduationprojectandroid.fragments.for_main_page.adapters.MarketItemsAdapter
import com.example.graduationprojectandroid.network.DataService


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

        DataService.getMarketItems{
            marketItemsList.layoutManager = GridLayoutManager(marketItemsList.context, 3)
            marketItemsList.adapter = MarketItemsAdapter(
                parentFragmentManager, it)

            moneyText.text = money.toString()
        }
    }



    companion object {
        @JvmStatic
        fun newInstance(money: Int) =
            MarketList(money)
    }
}