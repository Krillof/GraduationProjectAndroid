package com.example.graduationprojectandroid.fragments.for_main_page.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.databinding.SimpleLayoutMarketItemBinding

class MarketItemsAdapter (
    private var items_arr: ArrayList<MarketItem>
) : RecyclerView.Adapter<MarketItemsAdapter.ItemView>()
{

    class ItemView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = SimpleLayoutMarketItemBinding.bind(itemView)

        fun bind(item: MarketItem) = with(binding) {
            //TODO: У предметов будут картинки - их нужно как-то выводить

            moneyText.visibility = item.getVisibility()
            marketItemBackground.visibility = item.getVisibility()
            blackWhiteMoneyLogo.visibility = item.getVisibility()


            moneyText.text = item.getMoney().toString()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarketItemsAdapter.ItemView {
        return MarketItemsAdapter.ItemView(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.simple_layout_market_item, parent, false)
        )
    }

    override fun getItemCount(): Int = items_arr.size

    override fun onBindViewHolder(holder: MarketItemsAdapter.ItemView, position: Int) {
        holder.bind(items_arr[position])
    }
}