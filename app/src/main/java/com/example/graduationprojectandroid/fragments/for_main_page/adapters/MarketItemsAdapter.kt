package com.example.graduationprojectandroid.fragments.for_main_page.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.databinding.SimpleLayoutMarketItemBinding
import com.example.graduationprojectandroid.data.Items.MarketItem
import com.example.graduationprojectandroid.fragments.for_main_page.BuyDialogue
import com.example.graduationprojectandroid.network.DataService

class MarketItemsAdapter (
    private var fragment_manager: FragmentManager,
    private var items_arr: ArrayList<MarketItem>
) : RecyclerView.Adapter<MarketItemsAdapter.ItemView>()
{

    class ItemView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = SimpleLayoutMarketItemBinding.bind(itemView)

        fun bind(fragment_manager: FragmentManager, marketItem: MarketItem)
        = with(binding) {
            moneyText.visibility = marketItem.visibility
            marketItemBackground.visibility = marketItem.visibility
            blackWhiteMoneyLogo.visibility = marketItem.visibility
            moneyText.visibility = marketItem.visibility
            marketItemBackground.visibility = marketItem.visibility
            picture.visibility = marketItem.visibility

            if (marketItem.visibility == View.VISIBLE) {
                //TODO: check: NetworkService or DataService?
                DataService.setMarketItemPicture(marketItem.id, picture)
            }

            moneyText.text = marketItem.money.toString()

            if (marketItem.visibility == View.VISIBLE)
                layout.setOnClickListener {
                    var df: DialogFragment? = null
                    df = BuyDialogue(marketItem) {
                        if (it){
                            //TODO: Buy an marketItem by DataService
                        }

                        df?.dismiss()
                    }
                    df.show(fragment_manager, "save_changes")
                }

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

    override fun onBindViewHolder(holder: ItemView, position: Int) {
        holder.bind(fragment_manager, items_arr[position])
    }
}